package com.nttdata.credit.business.impl;

import com.nttdata.credit.business.CustomerService;
import com.nttdata.credit.client.CustomerClient;
import com.nttdata.credit.model.customer.Customer;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * Class: CustomerServiceImpl. <br/>
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
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerClient customerClient;

  @Override
  public Mono<Customer> findCustomer(BigInteger documentNumber) {
    return customerClient.getCustomer(documentNumber)
      .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Customer not found - documentNumber: ".concat(documentNumber.toString())))))
      .doOnSuccess(customerEntity -> log.info("Successful find - documentNumber: "
        .concat(documentNumber.toString())));

  }

}
