package com.users.Users.enums;

public enum UserStatus {
    ACTIVE,
    INACTIVE,
    REJECTED;
    
    public static UserStatus fromString(String status) {
        if (status == null) return null;
        return switch(status.toLowerCase()) {
            case "active" -> ACTIVE;
            case "inactive" -> INACTIVE;
            default -> null;
        };
    }
}

