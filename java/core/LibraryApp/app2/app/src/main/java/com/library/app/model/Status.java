package com.library.app.model;

public enum Status {
    ACTIVE("A"),
    INACTIVE("I");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static Status fromValue(String value) {
        for (Status s : Status.values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}



