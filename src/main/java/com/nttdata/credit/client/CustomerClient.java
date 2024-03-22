package com.nttdata.credit.client;

import com.nttdata.credit.model.customer.Customer;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Class: CustomerClient. <br/>
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
@Component
public class CustomerClient {

  @Value("${microservices.customer.urlPaths.getCustomer}")
  private String urlPathGetCustomer;

  public Mono<Customer> getCustomer(BigInteger documentNumber) {
    return WebClient.create(urlPathGetCustomer)
      .get()
      .uri(uriBuilder -> uriBuilder
        .queryParam("documentNumber", documentNumber)
        .build())
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(Customer.class);
  }
}
