package com.nttdata.credit.repository;

import com.nttdata.credit.model.Credit;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: CreditRepository. <br/>
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
public interface CreditRepository {

  Mono<Credit> findCreditById(String creditId);

  Mono<Credit> findCredit(BigInteger creditNumber);

  Flux<Credit> findCredits(BigInteger customerDocument);

  Mono<Boolean> findExistsCredit(String type, BigInteger customerDocument);

  Mono<Boolean> findExistsCredit(BigInteger creditNumber);

  Mono<Credit> saveCredit(Credit credit);

}
