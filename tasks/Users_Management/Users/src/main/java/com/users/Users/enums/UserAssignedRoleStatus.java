package com.users.Users.enums;

public enum UserAssignedRoleStatus {
    ACTIVE,
    INACTIVE;
	
    public static UserAssignedRoleStatus fromString(String status) {
        if (status == null) return null;
        return switch(status.toLowerCase()) {
            case "active" -> ACTIVE;
            case "inactive" -> INACTIVE;
            default -> null;
        };
    }
}

