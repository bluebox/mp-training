package com.medplus.lms.domain;

public enum Gender {
    MALE("M", "Male"),
    FEMALE("F", "Female"),
    OTHER("O", "Other");

    private final String code;
    private final String description;

    Gender(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() { return code; }
    public String getDescription() { return description; }

    public static Gender fromCode(String code) {
        for (Gender g : values()) {
            if (g.getCode().equalsIgnoreCase(code)) return g;
        }
        return null;
    }
}
	