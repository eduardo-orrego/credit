package com.nttdata.credit.repository;

import com.nttdata.credit.model.credit.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {
    Mono<Credit> findByCreditNumber(String creditNumber);

    Flux<Credit> findByHolderId(String holderId);

    Mono<Boolean> existsByTypeAndCreditHoldersHolderId(String type, String holderId);

}
