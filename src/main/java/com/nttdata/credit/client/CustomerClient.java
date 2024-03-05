package com.nttdata.credit.client;

import com.nttdata.credit.model.customer.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CustomerClient {

    @Value("${microservices.customer.urlPaths.getCustomerById}")
    private String urlPathGetCustomerById;

    public Mono<Customer> getCustomerById(String customerId) {
        return WebClient.create()
            .get()
            .uri(urlPathGetCustomerById, customerId)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Customer.class);
    }
}
