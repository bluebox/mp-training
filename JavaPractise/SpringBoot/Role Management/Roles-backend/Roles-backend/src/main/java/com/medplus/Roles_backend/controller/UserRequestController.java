package com.medplus.Roles_backend.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medplus.Roles_backend.domain.ActiveMembers;
import com.medplus.Roles_backend.domain.UserRequest;
import com.medplus.Roles_backend.dto.ApiResponse;
import com.medplus.Roles_backend.service.UserRequestServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "http://localhost:5173")
public class UserRequestController {

	@Autowired
	private UserRequestServiceInterface service;

	@PostMapping("/requests")
	public ResponseEntity<ApiResponse<Void>> sendRequest(@RequestBody @Valid UserRequest request) {
		service.sendRequest(request);
		return ResponseEntity.ok(new ApiResponse<>(true, "User details sent successfully!", null));
	}

	@GetMapping("/requests")
	public ResponseEntity<ApiResponse<List<UserRequest>>> getAllRequests() {
		List<UserRequest> requests = service.getAllRequests();
		return ResponseEntity.ok(new ApiResponse<>(true, "Fetched successfully", requests));
	}

	@GetMapping("/getallactiveusers")
	public ResponseEntity<ApiResponse<List<ActiveMembers>>> getAllActiveUsers() {
		return ResponseEntity.ok(new ApiResponse<>(true, "Fetched successfully", service.getAllActiveUsers()));
	}

	@PostMapping("/requests/{reqId}/accept")
	public ResponseEntity<ApiResponse<Void>> acceptRequest(@PathVariable Long reqId) {
		service.acceptRequest(reqId);
		return ResponseEntity.ok(new ApiResponse<>(true, "Request accepted successfully!", null));
	}

	@PostMapping("/requests/{reqId}/reject")
	public ResponseEntity<ApiResponse<Void>> rejectRequest(@PathVariable Long reqId) {
		service.rejectRequest(reqId);
		return ResponseEntity.ok(new ApiResponse<>(true, "Request rejected successfully!", null));
	}

	@PostMapping("/requests/{reqId}/active")
	public ResponseEntity<ApiResponse<Void>> activeRequest(@PathVariable Long reqId) {
		service.activeRequest(reqId);
		return ResponseEntity.ok(new ApiResponse<>(true, "Request activated successfully!", null));
	}

	@PostMapping("/requests/{reqId}/inactive")
	public ResponseEntity<ApiResponse<Void>> inactiveRequest(@PathVariable Long reqId) {
		service.inactiveRequest(reqId);
		return ResponseEntity.ok(new ApiResponse<>(true, "Request deactivated successfully!", null));
	}
}
