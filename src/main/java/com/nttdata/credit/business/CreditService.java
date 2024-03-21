package com.nttdata.credit.business;

import com.nttdata.credit.api.request.CreditRequest;
import com.nttdata.credit.model.Credit;
import java.math.BigInteger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: CreditService. <br/>
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
public interface CreditService {

  Mono<Credit> saveCredit(CreditRequest credit);

  Mono<Credit> updateCredit(CreditRequest credit, String creditId);

  Mono<Credit> getCreditByCreditNumber(BigInteger creditNumber);

  Flux<Credit> getCreditsByCustomerDocument(BigInteger customerDocument);

}
