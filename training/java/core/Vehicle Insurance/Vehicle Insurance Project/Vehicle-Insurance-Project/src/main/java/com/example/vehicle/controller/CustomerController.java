package com.example.vehicle.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicle.model.Customer;
import com.example.vehicle.service.CustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService service;
//	@PreAuthorize(value = "hasRole('admin')")
	@PostMapping("/add")
	public String createCustomer(@RequestBody Customer c) throws Exception {
		return service.addCustomer(c);
	}
	@PutMapping("/update")
	public String updateCustomer(@RequestBody Customer c) throws Exception {
		return service.updateCustomer(c);
	}
	@PutMapping("/updateStatus")
	public String updateCustomerStatus(@RequestParam int customerId,@RequestParam char status) throws Exception {
		return service.updateCustomerStatus(customerId, status);
	}
	@PutMapping("/delete")
	public String deleteCustomer(@RequestParam int customerId) throws Exception {
		return service.deleteCustomerById(customerId);
	}
	@GetMapping("/show")
	public Customer getCustomerDetails(@RequestParam int customerId) throws Exception {
		return service.getCustomerById(customerId);
	}
//	@PreAuthorize(value = "hasRole('admin')")
	@GetMapping("/showAll")
	public List<Customer> getAllCustomers() throws SQLException {
		return service.getAllCustomers();
	}
}
