package com.medplus.Roles_backend.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.medplus.Roles_backend.domain.User;
import com.medplus.Roles_backend.dto.ApiResponse;

public interface UserServiceInterface {
	ResponseEntity<ApiResponse<Map<String, Object>>> login(User loginRequest);

    ResponseEntity<ApiResponse<Void>> changePassword(String userId, String newPassword);
}
