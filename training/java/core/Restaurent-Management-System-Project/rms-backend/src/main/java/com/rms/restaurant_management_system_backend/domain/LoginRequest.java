package com.rms.restaurant_management_system_backend.domain;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String password;
}
