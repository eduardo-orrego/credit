package com.nttdata.credit.business;

import com.nttdata.credit.model.CreditMovement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditMovementService {
    Mono<CreditMovement> saveMovementCredit(CreditMovement creditMovement);

    Flux<CreditMovement> getMovementsCredit(String creditNumber);
}
