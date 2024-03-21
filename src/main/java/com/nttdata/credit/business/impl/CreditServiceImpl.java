package com.nttdata.credit.business.impl;

import com.nttdata.credit.api.request.CreditRequest;
import com.nttdata.credit.builder.CreditBuilder;
import com.nttdata.credit.business.CreditService;
import com.nttdata.credit.business.CustomerService;
import com.nttdata.credit.business.ProductService;
import com.nttdata.credit.enums.CustomerTypeEnum;
import com.nttdata.credit.model.Credit;
import com.nttdata.credit.model.customer.Customer;
import com.nttdata.credit.repository.CreditRepository;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class: CreditServiceImpl. <br/>
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
public class CreditServiceImpl implements CreditService {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private CreditRepository creditRepository;

  @Autowired
  private ProductService productService;

  @Override
  public Mono<Credit> saveCredit(CreditRequest creditRequest) {
    return creditRepository.findExistsCredit(creditRequest.getCreditNumber())
      .flatMap(aBoolean -> {
        if (Boolean.FALSE.equals(aBoolean)) {
          return customerService.findCustomer(creditRequest.getCustomerDocument())
            .flatMap(customerData -> this.validateCredit(creditRequest, customerData))
            .flatMap(accountValidated -> productService.findProduct(creditRequest.getType().name())
              .map(product -> CreditBuilder.toCreditEntity(accountValidated, product)))
            .flatMap(creditRepository::saveCredit);
        }
        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "There is another Credit with the same Credit Number: "
            .concat(creditRequest.getCreditNumber().toString())));
      });
  }

  @Override
  public Mono<Credit> updateCredit(CreditRequest creditRequest, String creditId) {
    return creditRepository.findCreditById(creditId)
      .flatMap(creditCurrent -> {
        if (creditRequest.getCreditNumber().compareTo(creditCurrent.getCreditNumber()) == 0) {
          return creditRepository.saveCredit(CreditBuilder.toCreditEntity(creditRequest,
            creditCurrent));
        }
        return this.saveCredit(creditRequest);
      })
      .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Credit not found - creditId: ".concat(creditId)))));

  }

  @Override
  public Mono<Credit> getCreditByCreditNumber(BigInteger creditNumber) {
    return creditRepository.findCredit(creditNumber)
      .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Credit not found - creditNumber: ".concat(creditNumber.toString())))));
  }

  @Override
  public Flux<Credit> getCreditsByCustomerDocument(BigInteger customerDocument) {
    return creditRepository.findCredits(customerDocument)
      .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Credit not found - customerDocument: ".concat(customerDocument.toString())))));
  }

  private Mono<CreditRequest> validateCredit(CreditRequest creditRequest, Customer customerData) {

    if (customerData.getType().equals(CustomerTypeEnum.PERSONAL.name())) {
      return creditRepository.findExistsCredit(creditRequest.getType().name(),
          customerData.getIdentificationDocument().getNumber())
        .flatMap(existsCredit -> Boolean.TRUE.equals(existsCredit)
          ? Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "El Cliente Personal ya cuenta con un credito"))
          : Mono.just(creditRequest));
    }
    return Mono.just(creditRequest);
  }

}


