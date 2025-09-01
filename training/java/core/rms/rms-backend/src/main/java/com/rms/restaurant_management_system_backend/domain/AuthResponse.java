package com.rms.restaurant_management_system_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	private String token;
	private String role;

}
