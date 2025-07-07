package com.library.domain;


public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Gender fromCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Gender code cannot be null");
        }
        switch (code.toUpperCase()) {
            case "M": return MALE;
            case "F": return FEMALE;
            default: throw new IllegalArgumentException("Invalid gender code: " + code);
        }
    }
}
