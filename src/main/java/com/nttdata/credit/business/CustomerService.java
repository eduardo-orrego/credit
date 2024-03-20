package com.nttdata.credit.business;

import com.nttdata.credit.model.customer.Customer;
import java.math.BigInteger;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> findCustomer(BigInteger documentNumber);
}
