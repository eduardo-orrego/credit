package com.nttdata.credit.repository;

import com.nttdata.credit.model.CreditMovement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CreditMovementRepository extends ReactiveMongoRepository<CreditMovement, String> {

    Flux<CreditMovement> findByCreditNumber(String creditId);

}
