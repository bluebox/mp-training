package com.medplus.Roles_backend.dto;

import lombok.Data;

@Data
public class PasswordChangeRequest {
	private String userId;
    private String newPassword;
}
