package com.medplus.Roles_backend.enums;

public enum ApprovalStatus {
    PENDING("P", "Pending"),
    APPROVED("A", "Approved"),
    REJECTED("R", "Rejected");

    private final String code;
    private final String description;

    ApprovalStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static ApprovalStatus fromCode(String code) {
        for (ApprovalStatus status : values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        return null;
    }
}
