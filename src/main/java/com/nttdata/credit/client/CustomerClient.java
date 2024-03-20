package com.nttdata.credit.client;

import com.nttdata.credit.model.customer.Customer;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CustomerClient {

    @Value("${microservices.customer.urlPaths.getCustomer}")
    private String urlPathGetCustomer;

    public Mono<Customer> getCustomer(BigInteger documentNumber) {
        return WebClient.create()
            .get()
            .uri(uriBuilder -> uriBuilder
                .path(urlPathGetCustomer)
                .queryParam("documentNumber", documentNumber)
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(Customer.class);
    }
}
