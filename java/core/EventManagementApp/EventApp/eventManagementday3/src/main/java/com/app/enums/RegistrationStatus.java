package com.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RegistrationStatus {
    ATTENDED("A"),
    REGISTERED("R"),
    CANCELLED("C"),
    NOT_ATTENDED("N");

    private final String code;

    RegistrationStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static RegistrationStatus fromCode(String code) {
        for (RegistrationStatus r : values()) {
            if (r.code.equalsIgnoreCase(code)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown RegistrationStatus: " + code);
    }
}

