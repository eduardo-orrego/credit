package com.nttdata.credit.business;

import com.nttdata.credit.model.credit.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
    Mono<Credit> getCreditByCreditNumber(String creditNumber);

    Flux<Credit> getCreditsByCustomerId(String customerId);

    Mono<Credit> saveCredit(Credit credit);
}
