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
        if (code == null) return null;
        switch (code.toUpperCase()) {
            case "A":
            case "ACTIVE":
                return ACTIVE;
            case "F":
            case "FINISHED":
                return FINISHED;
            case "C":
            case "CANCELLED":
                return CANCELLED;
            default:
                throw new IllegalArgumentException("Invalid event status: " + code);
        }
    }
}