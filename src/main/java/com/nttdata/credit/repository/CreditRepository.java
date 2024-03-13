package com.nttdata.credit.repository;

import com.nttdata.credit.model.Credit;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepository {

    Mono<Credit> findCredit(BigInteger creditNumber);

    Flux<Credit> findCredit(String customerId);

    Mono<Boolean> findExistsCredit(String type, String customerId);

    Mono<Boolean> findExistsCredit(String creditId);

    Mono<Credit> saveCredit(Credit credit);

}
