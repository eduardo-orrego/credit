package com.nttdata.credit.business;

import com.nttdata.credit.model.customer.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> getCustomerById(String customerId);
}
