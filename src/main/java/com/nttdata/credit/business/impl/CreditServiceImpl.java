package com.nttdata.credit.business.impl;

import com.nttdata.credit.business.CreditService;
import com.nttdata.credit.business.CustomerService;
import com.nttdata.credit.model.credit.Credit;
import com.nttdata.credit.model.credit.CreditHolder;
import com.nttdata.credit.model.enums.CustomerTypeEnum;
import com.nttdata.credit.model.enums.HolderTypeEnum;
import com.nttdata.credit.repository.CreditRepository;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<Credit> getCreditByCreditNumber(BigInteger creditNumber) {
        return creditRepository.findByCreditNumber(creditNumber)
            .switchIfEmpty(Mono.error(new RuntimeException("Numero de credito no existe")));
    }

    @Override
    public Flux<Credit> getCreditsByCustomerId(String customerId) {
        return creditRepository.findByCreditHoldersHolderId(customerId)
            .switchIfEmpty(Mono.error(new RuntimeException("No se encontraron creditos asociados al cliente "
                .concat(customerId))));
    }

    @Override
    public Mono<Credit> saveCredit(Credit credit) {
        return this.validateBusinessCredit(credit)
            .flatMap(creditRepository::save);
    }

    public Mono<Credit> validateBusinessCredit(Credit creditData) {
        String customerId = creditData.getCreditHolders().stream()
            .filter(creditHolder -> creditHolder.getHolderType().name().equals(HolderTypeEnum.PRIMARY.name()))
            .findFirst().map(CreditHolder::getHolderId).orElse("");

        String creditType = creditData.getType().name();

        return customerService.getCustomerById(customerId)
            .switchIfEmpty(Mono.error(new RuntimeException("No se encontraron datos del titular")))

            .flatMap(customerData -> {
                if (customerData.getType().equals(CustomerTypeEnum.PERSONAL.name())) {
                    return creditRepository.existsByTypeAndCreditHoldersHolderId(creditType, customerId)
                        .flatMap(existsCredit -> {
                            if (Boolean.TRUE.equals(existsCredit)) {
                                return Mono.error(new RuntimeException("El Cliente Personal ya tiene un " +
                                    "credit del tipo ".concat(creditType)));
                            } else {
                                return Mono.just(creditData);
                            }
                        });
                } else {
                    return Mono.just(creditData);
                }
            });
    }
}


