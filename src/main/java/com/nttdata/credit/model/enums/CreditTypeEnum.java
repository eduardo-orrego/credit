package com.nttdata.credit.model.enums;

import lombok.Getter;

@Getter
public enum CreditTypeEnum {
    PERSONAL_CREDIT("personal_credit"),
    BUSINESS_CREDIT("business_credit"),
    PERSONAL_CREDIT_CARD("personal_credit_card"),
    BUSINESS_CREDIT_CARD("business_credit_card");

    private String value;

    CreditTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CreditTypeEnum fromValue(String value) {
        for (CreditTypeEnum b : CreditTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
