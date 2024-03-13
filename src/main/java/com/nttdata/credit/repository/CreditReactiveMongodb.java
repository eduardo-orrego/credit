package com.nttdata.credit.repository;

import com.nttdata.credit.model.Credit;
import java.math.BigInteger;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CreditReactiveMongodb extends ReactiveMongoRepository<Credit, String> {

    Mono<Credit> findByCreditNumber(BigInteger creditNumber);

    Flux<Credit> findByCustomerId(String customerId);

    Mono<Boolean> existsByTypeAndCustomerId(String type, String customerId);

}
