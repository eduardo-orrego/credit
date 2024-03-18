package com.nttdata.credit.business;

import com.nttdata.credit.model.Product;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> findProduct(String typeProduct);

}
