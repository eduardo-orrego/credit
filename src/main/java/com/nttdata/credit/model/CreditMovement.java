package com.nttdata.credit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.credit.model.enums.CreditTransactionTypeEnum;
import com.nttdata.credit.model.enums.CurrencyTypeEnum;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credit_movement")
public class CreditMovement {

    @Id
    private String id;

    @JsonProperty(value = "transaction_type")
    @Enumerated(EnumType.STRING)
    private CreditTransactionTypeEnum transactionType;

    @JsonProperty(value = "transaction_date")
    private LocalDateTime transactionDate;

    @JsonProperty(value = "credit_number")
    private BigInteger creditNumber;

    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @JsonProperty(value = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyTypeEnum currency;

}