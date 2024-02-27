package com.nttdata.credit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.credit.model.enums.CreditTypeEnum;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Document(collection = "credit")
public class Credit {

    @Id
    private String id;

    @JsonProperty(value = "credit_type")
    @Enumerated(EnumType.STRING)
    private CreditTypeEnum creditType;

    @JsonProperty(value = "credit_number")
    private BigInteger creditNumber;

    @JsonProperty(value = "credit_card")
    private CreditCard creditCard;

    @JsonProperty(value = "credit_amount")
    private BigDecimal creditAmount;

    @JsonProperty(value = "holder_id")
    private String holderId;

    @JsonProperty(value = "available_credit_balance")
    private BigDecimal availableCreditBalance;

    @JsonProperty(value = "current_credit_balance")
    private BigDecimal currentCreditBalance;

    @JsonProperty(value = "interest_rate")
    private BigDecimal interestRate;

}
