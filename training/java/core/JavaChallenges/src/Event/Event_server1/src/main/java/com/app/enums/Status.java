package com.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    ACTIVE("A"),
    INACTIVE("I");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static Status fromCode(String code) {
        if (code == null) return null;
        switch (code.toUpperCase()) {
            case "A":
                return ACTIVE;
            case "I":
                return INACTIVE;
            default:
                throw new IllegalArgumentException("Invalid status code: " + code);
        }
    }
}
