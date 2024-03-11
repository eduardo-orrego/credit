package com.nttdata.credit.business.impl;

import com.nttdata.credit.api.request.CreditRequest;
import com.nttdata.credit.builder.CreditBuilder;
import com.nttdata.credit.business.CreditService;
import com.nttdata.credit.business.CustomerService;
import com.nttdata.credit.enums.CustomerTypeEnum;
import com.nttdata.credit.model.Credit;
import com.nttdata.credit.repository.CreditRepository;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final CustomerService customerService;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository, CustomerService customerService) {
        this.creditRepository = creditRepository;
        this.customerService = customerService;
    }

    @Override
    public Mono<Credit> saveCredit(CreditRequest credit) {
        return this.validateBusinessCredit(credit)
            .map(creditRequest -> CreditBuilder.toCreditEntity(creditRequest, null))
            .flatMap(creditRepository::save)
            .doOnSuccess(customer -> log.info("Successful update - creditId: ".concat(customer.getId())));
    }

    @Override
    public Mono<Credit> updateCredit(CreditRequest creditRequest, String creditId) {
        return creditRepository.existsById(creditId)
            .flatMap(aBoolean -> {
                if (Boolean.TRUE.equals(aBoolean)) {
                    return creditRepository.save(CreditBuilder.toCreditEntity(creditRequest, creditId));
                }
                return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit not found - "
                    + "creditId: ".concat(creditId)));
            })
            .doOnSuccess(account -> log.info("Successful update - creditId: ".concat(creditId)));
    }

    @Override
    public Mono<Credit> getCreditByCreditNumber(BigInteger creditNumber) {
        return creditRepository.findByCreditNumber(creditNumber)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit not found - "
                + "creditNumber: ".concat(creditNumber.toString()))))
            .doOnSuccess(customer -> log.info("Successful search - creditNumber: ".concat(creditNumber.toString())));
    }

    @Override
    public Flux<Credit> getCreditsByCustomerId(String customerId) {
        return creditRepository.findByCustomerId(customerId)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit not found - "
                + "customerId: ".concat(customerId))))
            .doOnComplete(() -> log.info("Successful search - creditNumber: ".concat(customerId)));
    }

    private Mono<CreditRequest> validateBusinessCredit(CreditRequest creditRequest) {

        return customerService.getCustomerById(creditRequest.getCustomerId())
            .flatMap(customerData -> {
                if (customerData.getType().equals(CustomerTypeEnum.PERSONAL.name())) {
                    return creditRepository.existsByTypeAndCustomerId(creditRequest.getType().name(),
                            customerData.getId())
                        .flatMap(existsCredit ->
                            Boolean.TRUE.equals(existsCredit)
                                ? Mono.error(new RuntimeException("El Cliente Personal ya tiene cuenta con credito"))
                                : Mono.just(creditRequest)
                        );
                }
                return Mono.just(creditRequest);
            })
            .switchIfEmpty(Mono.error(new RuntimeException("No se encontraron datos del titular")));

    }

}


