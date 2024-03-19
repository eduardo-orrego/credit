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
    CreditRepositoryImpl(CreditReactiveMongodb creditReactiveMongodb) {
        this.creditReactiveMongodb = creditReactiveMongodb;
    }

    @Override
    public Mono<Credit> findCreditById(String creditId) {
        return creditReactiveMongodb.findById(creditId)
            .doOnSuccess(credit -> log.info("Successful find - creditId: ".concat(creditId)));
    }

    @Override
    public Mono<Credit> findCredit(BigInteger creditNumber) {
        return creditReactiveMongodb.findByCreditNumber(creditNumber)
            .doOnSuccess(credit -> log.info("Successful find - creditNumber: ".concat(creditNumber.toString())));
    }

    @Override
    public Flux<Credit> findCredits(BigInteger customerDocument) {
        return creditReactiveMongodb.findByCustomerDocument(customerDocument)
            .doOnComplete(() -> log.info("Successful find - customerId: ".concat(customerDocument.toString())));
    }

    @Override
    public Mono<Boolean> findExistsCredit(String type, String customerId) {
        return creditReactiveMongodb.existsByTypeAndCustomerId(type, customerId)
            .doOnSuccess(exists -> log.info("Successful find exists - customerId: ".concat(customerId)));
    }

    @Override
    public Mono<Boolean> findExistsCredit(BigInteger creditNumber) {
        return creditReactiveMongodb.existsByCreditNumber(creditNumber)
            .doOnSuccess(exists -> log.info("Successful find exists - creditNumber: ".concat(creditNumber.toString())));
    }

    @Override
    public Mono<Credit> saveCredit(Credit credit) {
        return creditReactiveMongodb.save(credit)
            .doOnSuccess(creditEntity -> log.info("Successful save - creditId: ".concat(creditEntity.getId())));
    }
}
