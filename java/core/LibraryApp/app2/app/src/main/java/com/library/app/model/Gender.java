package com.library.app.model;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.getValue().equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown gender value: " + value);
    }
}


