package com.nttdata.credit.model.credit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nttdata.credit.model.enums.HolderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditHolder {

    @JsonProperty(value = "holderId")
    private String holderId;

    @JsonProperty(value = "holderType")
    private HolderTypeEnum holderType;

}
