package com.medplus.lms.domain;

public enum Status {
    ACTIVE("A", "Active"),
    INACTIVE("I", "Inactive");

    private final String code;
    private final String description;

    Status(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() { return code; }
    public String getDescription() { return description; }

    public static Status fromCode(String code) {
        for (Status s : values()) {
            if (s.getCode().equalsIgnoreCase(code)) return s;
        }
        return null;
    }
   
}
