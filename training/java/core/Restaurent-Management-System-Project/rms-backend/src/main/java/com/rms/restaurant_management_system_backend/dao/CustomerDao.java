package com.rms.restaurant_management_system_backend.dao;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.Customer;

public interface CustomerDao {
	int addCustomer(Customer customer);
	
	public List<Customer> getAllCustomers();
	
	Customer getCustomerById(int id);
}
