package com.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EventStatus {
    ACTIVE("A"),
    FINISHED("F"),
    CANCELLED("C");

    private final String code;

    EventStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static EventStatus fromCode(String code) {
        for (EventStatus status: values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown EventStatus: " + code);
    }
}