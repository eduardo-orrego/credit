package com.nttdata.credit.api.request;

import com.nttdata.credit.enums.CreditTypeEnum;
import com.nttdata.credit.enums.CurrencyTypeEnum;
import com.nttdata.credit.enums.StatusTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "El campo 'customerId' no puede ser vacío")
    private String customerId;

    private BigInteger creditNumber;

    @NotNull(message = "El campo 'disbursementDate' no puede ser nulo")
    private LocalDate disbursementDate;

    @NotNull(message = "El campo 'amount' no puede ser nulo")
    private BigDecimal amount;

    private BigDecimal outstandingBalance;

    @NotNull(message = "El campo 'currency' no puede ser nulo")
    private CurrencyTypeEnum currency;

    private BigDecimal interestRate;

    @NotNull(message = "El campo 'dueDate' no puede ser nulo")
    private LocalDate dueDate;

}
