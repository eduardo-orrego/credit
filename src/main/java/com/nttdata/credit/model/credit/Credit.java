package com.nttdata.credit.model.credit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.credit.model.enums.CreditTypeEnum;
import com.nttdata.credit.model.enums.CurrencyTypeEnum;
import com.nttdata.credit.model.enums.StatusTypeEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

    @JsonProperty(value = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private CreditTypeEnum type;

    @JsonProperty(value = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusTypeEnum status;

    @JsonProperty(value = "creditNumber")
    private BigInteger creditNumber;

    @JsonProperty(value = "outstandingBalance")
    private BigDecimal outstandingBalance;

    @JsonProperty(value = "openingDate")
    @NotNull
    private LocalDate openingDate;

    @JsonProperty(value = "lastTransactionDate")
    private LocalDate lastTransactionDate;

    @JsonProperty(value = "currency")
    @NotNull
    private CurrencyTypeEnum currency;

    @JsonProperty(value = "interestRate")
    private BigDecimal interestRate;

    @NotEmpty
    @JsonProperty(value = "creditHolders")
    private List<CreditHolder> creditHolders;

    @NotNull
    @JsonProperty(value = "authorizedSigners")
    private List<AuthorizedSigner> authorizedSigners;

    @JsonProperty(value = "loanInfo")
    private LoanInfo loanInfo;

    @JsonProperty(value = "creditCardInfo")
    private CreditCardInfo creditCardInfo;

    @JsonProperty(value = "dateCreated")
    private LocalDateTime dateCreated;

    @JsonProperty(value = "lastUpdated")
    private LocalDateTime lastUpdated;

}
