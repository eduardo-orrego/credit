package com.nttdata.credit.builder;

import com.nttdata.credit.api.request.CreditRequest;
import com.nttdata.credit.model.Credit;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CreditBuilder {
    CreditBuilder() {
    }

    public static Credit toCreditEntity(CreditRequest creditRequest, String creditId) {

        return Credit.builder()
            .id(creditId)
            .type(creditRequest.getType().name())
            .status(creditRequest.getStatus().name())
            .customerId(creditRequest.getCustomerId())
            .creditNumber(Objects.nonNull(creditRequest.getCreditNumber())
                ? creditRequest.getCreditNumber()
                : generateCreditNumber())
            .disbursementDate(creditRequest.getDisbursementDate())
            .amount(creditRequest.getAmount())
            .outstandingBalance(new BigDecimal("0.00"))
            .currency(creditRequest.getCurrency().name())
            .interestRate(creditRequest.getInterestRate())
            .dueDate(creditRequest.getDueDate())
            .lastTransactionDate(LocalDateTime.now())
            .dateCreated(LocalDateTime.now())
            .lastUpdated(LocalDateTime.now())
            .build();
    }

    private static BigInteger generateCreditNumber() {
        String creditCard = "30".concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        return new BigInteger(creditCard);
    }
}
