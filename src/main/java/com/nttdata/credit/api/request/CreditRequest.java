package com.nttdata.credit.api.request;

import com.nttdata.credit.enums.CreditTypeEnum;
import com.nttdata.credit.enums.CurrencyTypeEnum;
import com.nttdata.credit.enums.StatusTypeEnum;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequest {

    private String id;
    private CreditTypeEnum type;
    private StatusTypeEnum status;
    private String customerId;
    private BigInteger creditNumber;
    private LocalDate disbursementDate;
    private BigDecimal amount;
    private BigDecimal outstandingBalance;
    private CurrencyTypeEnum currency;
    private BigDecimal interestRate;
    private LocalDate dueDate;

}
