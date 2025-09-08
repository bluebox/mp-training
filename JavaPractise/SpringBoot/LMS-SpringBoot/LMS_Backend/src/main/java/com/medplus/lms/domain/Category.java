package com.medplus.lms.domain;


public enum Category {
    COMEDY("COM", "Comedy"),
    FICTION("FIC", "Fiction"),
    MYSTERY("MYS", "Mystery"),
    HORROR("HOR", "Horror"),
    THRILLER("THR", "Thriller");

    private final String code;
    private final String description;

    Category(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() { return code; }
    public String getDescription() { return description; }

    public static Category fromCode(String code) {
        for (Category c : values()) {
            if (c.getCode().equalsIgnoreCase(code)) return c;
        }
        return null;
    }
}
