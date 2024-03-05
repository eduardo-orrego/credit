package com.nttdata.credit.business;

import com.nttdata.credit.model.credit.Credit;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
    Mono<Credit> getCreditByCreditNumber(BigInteger creditNumber);

    Flux<Credit> getCreditsByCustomerId(String customerId);

    Mono<Credit> saveCredit(Credit credit);
    Mono<Credit> updateCredit(Credit credit);
}
