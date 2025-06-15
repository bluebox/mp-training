package com.casestudy;

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
        for (Gender g : Gender.values()) {
            if (g.code.equalsIgnoreCase(code)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Invalid gender code: " + code);
    }
}
