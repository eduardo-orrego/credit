package com.nttdata.credit.builder;

import com.nttdata.credit.api.request.CreditRequest;
import com.nttdata.credit.model.Credit;
import com.nttdata.credit.model.Product;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Class: CreditBuilder. <br/>
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
public class CreditBuilder {
  CreditBuilder() {
  }

  public static Credit toCreditEntity(CreditRequest creditRequest, Product product) {

    return Credit.builder()
      .type(creditRequest.getType().name())
      .status(creditRequest.getStatus().name())
      .currency(creditRequest.getCurrency().name())
      .amount(creditRequest.getAmount())
      .disbursementDate(creditRequest.getDisbursementDate())
      .customerDocument(creditRequest.getCustomerDocument())
      .dueDate(creditRequest.getDueDate())
      .creditNumber(Objects.nonNull(creditRequest.getCreditNumber())
        ? creditRequest.getCreditNumber()
        : generateCardNumber())
      .outstandingBalance(Objects.nonNull(creditRequest.getOutstandingBalance())
        ? creditRequest.getOutstandingBalance()
        : BigDecimal.valueOf(0.00))
      .interestRate(Objects.nonNull(creditRequest.getInterestRate())
        ? creditRequest.getInterestRate()
        : product.getInterestRate())
      .availableBalance(Objects.nonNull(creditRequest.getAvailableBalance())
        ? creditRequest.getAvailableBalance()
        : BigDecimal.valueOf(0.00))
      .creditLimit(Objects.nonNull(creditRequest.getCreditLimit())
        ? creditRequest.getCreditLimit()
        : BigDecimal.valueOf(0.00))
      .lastTransactionDate(LocalDateTime.now())
      .dateCreated(LocalDateTime.now())
      .lastUpdated(LocalDateTime.now())
      .build();

  }

  public static Credit toCreditEntity(CreditRequest creditRequest, Credit credit) {
    return Credit.builder()
      .id(credit.getId())
      .type(creditRequest.getType().name())
      .status(creditRequest.getStatus().name())
      .currency(creditRequest.getCurrency().name())
      .amount(creditRequest.getAmount())
      .customerDocument(creditRequest.getCustomerDocument())
      .disbursementDate(Objects.nonNull(creditRequest.getDisbursementDate())
        ? creditRequest.getDisbursementDate()
        : credit.getDisbursementDate())
      .dueDate(Objects.nonNull(creditRequest.getDueDate())
        ? creditRequest.getDueDate()
        : credit.getDueDate())
      .creditNumber(Objects.nonNull(creditRequest.getCreditNumber())
        ? creditRequest.getCreditNumber()
        : credit.getCreditNumber())
      .outstandingBalance(Objects.nonNull(creditRequest.getOutstandingBalance())
        ? creditRequest.getOutstandingBalance()
        : credit.getOutstandingBalance())
      .interestRate(Objects.nonNull(creditRequest.getInterestRate())
        ? creditRequest.getInterestRate()
        : credit.getInterestRate())
      .availableBalance(Objects.nonNull(creditRequest.getAvailableBalance())
        ? creditRequest.getAvailableBalance()
        : credit.getAvailableBalance())
      .creditLimit(Objects.nonNull(creditRequest.getCreditLimit())
        ? creditRequest.getCreditLimit()
        : credit.getCreditLimit())
      .lastTransactionDate(credit.getLastTransactionDate())
      .dateCreated(credit.getDateCreated())
      .lastUpdated(LocalDateTime.now())
      .build();

  }

  private static BigInteger generateCardNumber() {
    String cardNumber = "20".concat(
      LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    return new BigInteger(cardNumber);
  }
}
