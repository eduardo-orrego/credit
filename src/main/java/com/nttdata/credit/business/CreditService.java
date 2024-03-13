package com.nttdata.credit.business;

import com.nttdata.credit.api.request.CreditRequest;
import com.nttdata.credit.model.Credit;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

    Mono<Credit> saveCredit(CreditRequest credit);

    Mono<Credit> updateCredit(CreditRequest credit, String creditId);

    Mono<Credit> getCreditByCreditNumber(BigInteger creditNumber);

    Flux<Credit> getCreditsByCustomerId(String customerId);

}
