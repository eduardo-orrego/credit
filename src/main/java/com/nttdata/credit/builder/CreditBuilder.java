package com.nttdata.credit.builder;

import com.nttdata.credit.api.request.CreditRequest;
import com.nttdata.credit.model.Credit;
import java.time.LocalDateTime;

public class CreditBuilder {
    CreditBuilder() {
    }

    public static Credit toCreditEntity(CreditRequest creditRequest, String creditId) {

        return Credit.builder()
            .id(creditId)
            .type(creditRequest.getType().name())
            .status(creditRequest.getStatus().name())
            .customerId(creditRequest.getCustomerId())
            .creditNumber(creditRequest.getCreditNumber())
            .disbursementDate(creditRequest.getDisbursementDate())
            .amount(creditRequest.getAmount())
            .outstandingBalance(creditRequest.getOutstandingBalance())
            .currency(creditRequest.getCurrency().name())
            .interestRate(creditRequest.getInterestRate())
            .dueDate(creditRequest.getDueDate())
            .lastTransactionDate(LocalDateTime.now())
            .dateCreated(LocalDateTime.now())
            .lastUpdated(LocalDateTime.now())
            .build();
    }

}
