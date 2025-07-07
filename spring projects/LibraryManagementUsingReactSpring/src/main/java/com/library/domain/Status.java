package com.library.domain;

public enum Status {
    ACTIVE("A"),
    INACTIVE("I");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Status fromCode(String code) {
        if (code == null) throw new IllegalArgumentException("Code cannot be null");
        switch (code.toUpperCase()) {
            case "A": return ACTIVE;
            case "I": return INACTIVE;
            default: throw new IllegalArgumentException("Invalid status code: " + code);
        }
    }
}
