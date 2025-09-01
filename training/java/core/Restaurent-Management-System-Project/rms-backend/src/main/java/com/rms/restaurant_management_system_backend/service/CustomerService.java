package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.Customer;

public interface CustomerService {
	
	int addCustomer(Customer customer);
	
	List<Customer>getAllCustomers();
	
	Customer getCustomerById(int id);
}
