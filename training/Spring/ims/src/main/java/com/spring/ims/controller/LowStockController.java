package com.spring.ims.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.ims.domain.EmployeeProducts;
import com.spring.ims.service.LowStockService;

@Controller
@RequestMapping("user-stock")
public class LowStockController {

	private LowStockService lowStockService;

	public LowStockController(LowStockService lowStockService) {
		this.lowStockService = lowStockService;
	}

	@PostMapping("/low-stock")
	public ResponseEntity<?> allOrders() {
		try {
			return ResponseEntity.ok(lowStockService.lowStock());
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping("/fetch-product-by-name")
	public ResponseEntity<?> fetchProduct(@RequestBody EmployeeProducts employeeProducts) {
		try {
			return ResponseEntity.ok(lowStockService.fetchProduct(employeeProducts));
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "error");
			errorResponse.put("message", "Something Went Wrong!!!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

}
