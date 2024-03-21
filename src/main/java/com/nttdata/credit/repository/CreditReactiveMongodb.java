package com.nttdata.credit.repository;

import com.nttdata.credit.model.Credit;
import java.math.BigInteger;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: CreditReactiveMongodb. <br/>
 * <b>Bootcamp NTTDATA</b><br/>
 *
 * @author NTTDATA
 * @version 1.0
 *   <u>Developed by</u>:
 *   <ul>
 *   <li>Developer Carlos</li>
 *   </ul>
 * @since 1.0
 */
@Repository
public interface CreditReactiveMongodb extends ReactiveMongoRepository<Credit, String> {

  Mono<Credit> findByCreditNumber(BigInteger creditNumber);

  Flux<Credit> findByCustomerDocument(BigInteger customerDocument);

  Mono<Boolean> existsByTypeAndCustomerDocument(String type, BigInteger customerDocument);

  Mono<Boolean> existsByCreditNumber(BigInteger creditNumber);

}
