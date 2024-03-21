package com.nttdata.credit.business;

import com.nttdata.credit.model.Product;
import reactor.core.publisher.Mono;

/**
 * Class: ProductService. <br/>
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
public interface ProductService {
  Mono<Product> findProduct(String typeProduct);

}
