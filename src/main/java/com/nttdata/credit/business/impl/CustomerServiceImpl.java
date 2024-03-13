package com.nttdata.credit.business.impl;

import com.nttdata.credit.business.CustomerService;
import com.nttdata.credit.client.CustomerClient;
import com.nttdata.credit.model.customer.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerClient customerClient;

    @Autowired
    private CustomerServiceImpl(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public Mono<Customer> findCustomer(String customerId) {
        return customerClient.getCustomerById(customerId)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Customer not found - customerId: ".concat(customerId))))
            .doOnSuccess(customerEntity -> log.info("Successful find - customerId: ".concat(customerEntity.getId())));

    }

}
