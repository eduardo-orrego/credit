package com.nttdata.credit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    @JsonProperty(value = "card_number")
    private String cardNumber;

    @JsonProperty(value = "expiration_date")
    private LocalDate expirationDate;

    @JsonProperty(value = "cvv")
    @Size(min = 3, max = 3)
    private String cvv;

    @JsonProperty(value = "holder_id")
    private String holderId;

}