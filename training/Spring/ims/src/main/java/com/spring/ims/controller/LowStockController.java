package com.spring.ims.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.ims.service.Implementation;

@Controller
public class LowStockController {

	private Implementation impl;

	public LowStockController(Implementation impl) {
		this.impl = impl;
	}

	@PostMapping("/low-stock")
	public ResponseEntity<?> allOrders() {
		try {
			return ResponseEntity.ok(impl.lowStock());
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}
