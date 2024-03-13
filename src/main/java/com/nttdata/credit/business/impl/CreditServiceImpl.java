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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Mono<Credit> saveCredit(CreditRequest credit) {
        return this.validatePersonalCredit(credit)
            .map(creditRequest -> CreditBuilder.toCreditEntity(creditRequest, null))
            .flatMap(creditRepository::saveCredit);
    }

    @Override
    public Mono<Credit> updateCredit(CreditRequest creditRequest, String creditId) {
        return creditRepository.findExistsCredit(creditId)
            .flatMap(aBoolean -> Boolean.TRUE.equals(aBoolean)
                ? creditRepository.saveCredit(CreditBuilder.toCreditEntity(creditRequest, creditId))
                : Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit not found - "
                    + "creditId: ".concat(creditId))));
    }

    @Override
    public Mono<Credit> getCreditByCreditNumber(BigInteger creditNumber) {
        return creditRepository.findCredit(creditNumber)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit not found - "
                + "creditNumber: ".concat(creditNumber.toString()))));
    }

    @Override
    public Flux<Credit> getCreditsByCustomerId(String customerId) {
        return creditRepository.findCredit(customerId)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Credit not found - "
                + "customerId: ".concat(customerId))));
    }

    private Mono<CreditRequest> validatePersonalCredit(CreditRequest creditRequest) {

        return customerService.getCustomerById(creditRequest.getCustomerId())
            .flatMap(customerData -> {
                if (customerData.getType().equals(CustomerTypeEnum.PERSONAL.name())) {
                    return creditRepository.findExistsCredit(creditRequest.getType().name(),
                            customerData.getId())
                        .flatMap(existsCredit -> Boolean.TRUE.equals(existsCredit)
                            ? Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "El Cliente Personal ya cuenta con un credito"))
                            : Mono.just(creditRequest));
                }
                return Mono.just(creditRequest);
            });

    }

}


