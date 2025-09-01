package com.rms.restaurant_management_system_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.custom_classes.WaiterDetailsSelector;
import com.rms.restaurant_management_system_backend.service.WaitersService;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

@RestController
@RequestMapping("/api/waiters")
public class WaitersController {

	private WaitersService waitersService;

	public WaitersController(WaitersService waitersService) {
		this.waitersService = waitersService;
	}

	@GetMapping("/available")
	public ResponseEntity<CustomResponse> getAllAvailableWaiters() {
		List<WaiterDetailsSelector> availableWaiters = waitersService.selectAvailableWaiters();
		CustomResponse responseBody = new CustomResponse(true, "Available waiters fetched succesfully",
				availableWaiters);
		return ResponseEntity.ok(responseBody);
	}
}
