package com.library.app.model;

public enum Availability {
    AVAILABLE("A"),
    ISSUED("I");

    private final String value;

    Availability(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static Availability fromValue(String value) {
        for (Availability a : Availability.values()) {
            if (a.getValue().equals(value)) {
                return a;
            }
        }
        throw new IllegalArgumentException("Invalid availability value: " + value);
    }
}


