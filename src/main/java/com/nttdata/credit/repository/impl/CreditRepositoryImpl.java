package com.nttdata.credit.repository.impl;

import com.nttdata.credit.model.Credit;
import com.nttdata.credit.repository.CreditReactiveMongodb;
import com.nttdata.credit.repository.CreditRepository;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class CreditRepositoryImpl implements CreditRepository {

    private final CreditReactiveMongodb creditReactiveMongodb;

    @Autowired
    private CreditRepositoryImpl(CreditReactiveMongodb creditReactiveMongodb) {
        this.creditReactiveMongodb = creditReactiveMongodb;
    }

    @Override
    public Mono<Credit> findCredit(BigInteger creditNumber) {
        return creditReactiveMongodb.findByCreditNumber(creditNumber)
            .doOnSuccess(credit -> log.info("Successful find - creditNumber: ".concat(creditNumber.toString())));

    }

    @Override
    public Flux<Credit> findCredit(String customerId) {
        return creditReactiveMongodb.findByCustomerId(customerId)
            .doOnComplete(() -> log.info("Successful find - customerId: ".concat(customerId)));

    }

    @Override
    public Mono<Boolean> findExistsCredit(String type, String customerId) {
        return creditReactiveMongodb.existsByTypeAndCustomerId(type, customerId)
            .doOnSuccess(exists -> log.info("Successful find exists - customerId: ".concat(customerId)));
    }

    @Override
    public Mono<Boolean> findExistsCredit(String creditId) {
        return creditReactiveMongodb.existsById(creditId)
            .doOnSuccess(exists -> log.info("Successful find exists - creditId: ".concat(creditId)));

    }

    @Override
    public Mono<Credit> saveCredit(Credit credit) {
        return creditReactiveMongodb.save(credit)
            .doOnSuccess(creditEntity -> log.info("Successful save - creditId: ".concat(creditEntity.getId())));
    }
}
