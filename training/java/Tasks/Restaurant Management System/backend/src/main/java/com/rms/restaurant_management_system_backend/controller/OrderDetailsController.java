package com.rms.restaurant_management_system_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.domain.OrderDetails;
import com.rms.restaurant_management_system_backend.service.implementation.OrderDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsServiceImpl orderDetailsServiceImpl;

	@GetMapping
	public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
		return ResponseEntity.ok(orderDetailsServiceImpl.selectAllOrderDetails());
	}

	@PostMapping
	public ResponseEntity<String> insertAllOrderDetails(@Valid @RequestBody List<OrderDetails> orderDetailsList) {
		orderDetailsServiceImpl.insertOrderDetails(orderDetailsList);
		return ResponseEntity.ok("Inserted Succesfully");
	}
}
