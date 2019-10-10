package com.tokegenerator.domain;

public enum TokenStatusEnum {
    VALIDATED("Validated"),
    UNVALIDATED("Unvalidated"),
    INACTIVE("Inactive");

    private String value;

    private TokenStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TokenStatusEnum getEnumByValue(String value) {
        for (TokenStatusEnum e : TokenStatusEnum.values()) {
            if (value.equals(e.getValue()))
                return e;
        }
        return null;
    }
}
