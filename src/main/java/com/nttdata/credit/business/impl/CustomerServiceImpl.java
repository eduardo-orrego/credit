package com.nttdata.credit.business.impl;

import com.nttdata.credit.business.CustomerService;
import com.nttdata.credit.client.CustomerClient;
import com.nttdata.credit.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerClient customerClient;

    @Autowired
    public CustomerServiceImpl(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }


    @Override
    public Mono<Customer> getCustomerById(String customerId) {
        return customerClient.getCustomerById(customerId);
    }
}
