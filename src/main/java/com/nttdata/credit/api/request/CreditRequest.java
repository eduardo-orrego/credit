package com.nttdata.credit.api.request;

import com.nttdata.credit.enums.CreditTypeEnum;
import com.nttdata.credit.enums.CurrencyTypeEnum;
import com.nttdata.credit.enums.StatusTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "El campo 'type' no puede ser nulo")
    private CreditTypeEnum type;

    @NotNull(message = "El campo 'status' no puede ser nulo")
    private StatusTypeEnum status;

    @NotNull(message = "El campo 'currency' no puede ser nulo")
    private CurrencyTypeEnum currency;

    @NotNull(message = "El campo 'customerDocument' no puede ser vacío")
    private BigInteger customerDocument;

    @NotNull(message = "El campo 'amount' no puede ser nulo")
    private BigDecimal amount;

    private LocalDate disbursementDate;
    private LocalDate dueDate;
    @Size(min = 16, max = 16)
    private BigInteger creditNumber;
    private BigDecimal outstandingBalance;
    private BigDecimal interestRate;
    private BigDecimal availableBalance;
    private BigDecimal creditLimit;

}
