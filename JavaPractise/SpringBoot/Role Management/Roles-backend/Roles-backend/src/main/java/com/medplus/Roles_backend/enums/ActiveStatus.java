package com.medplus.Roles_backend.enums;

public enum ActiveStatus {
    ACTIVE("A", "Active"),
    INACTIVE("I", "Inactive");

    private final String code;
    private final String description;

    ActiveStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static ActiveStatus fromCode(String code) {
        for (ActiveStatus status : values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        return null;
    }
}
