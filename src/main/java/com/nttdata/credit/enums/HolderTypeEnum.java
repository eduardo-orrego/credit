package com.nttdata.credit.enums;

import lombok.Getter;

@Getter
public enum HolderTypeEnum {
    PRIMARY("PRINCIPAL"),
    SECONDARY("SECONDARY");

    private String value;

    HolderTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static HolderTypeEnum fromValue(String value) {
        for (HolderTypeEnum b : HolderTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
