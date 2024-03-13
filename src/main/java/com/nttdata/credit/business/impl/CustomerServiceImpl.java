package com.nttdata.credit.business.impl;

import com.nttdata.credit.business.CustomerService;
import com.nttdata.credit.client.CustomerClient;
import com.nttdata.credit.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerClient customerClient;

    @Override
    public Mono<Customer> getCustomerById(String customerId) {
        return customerClient.getCustomerById(customerId)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Customer not found - customerId: ".concat(customerId))));
    }
}
