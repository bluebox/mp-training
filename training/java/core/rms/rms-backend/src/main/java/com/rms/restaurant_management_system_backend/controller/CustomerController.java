package com.rms.restaurant_management_system_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.dao.implementation.CustomerDaoImpl;
import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.service.implementation.CustomerServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;

	@Autowired
	private CustomerDaoImpl customerDao;

	@PostMapping()
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {

		return ResponseEntity.ok(Map.of("status", "success", "message", "customer added successfully"));
	}

	@GetMapping("/{phone}")
	public Integer getId(@PathVariable String phone) {
		return customerDao.getCustomerIdByNumber(phone);
	}
}