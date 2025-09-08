package com.medplus.lms.domain;


public enum IssueStatus {
    ISSUED("I", "Issued"),
    RETURNED("R", "Returned");

    private final String code;
    private final String description;

    IssueStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() { return code; }
    public String getDescription() { return description; }

    public static IssueStatus fromCode(String code) {
        for (IssueStatus i : values()) {
            if (i.getCode().equalsIgnoreCase(code)) return i;
        }
        return null;
    }
    
}
