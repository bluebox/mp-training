package com.rms.restaurant_management_system_backend.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.restaurant_management_system_backend.dao.implementation.CustomerDaoImpl;
import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;
import com.rms.restaurant_management_system_backend.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDaoImpl customerDaoImpl;

	@Override
	public int addCustomer(Customer customer) {

		if (customer == null) {
			throw new RestaurantOperationException("Please enter all customer details");
		}
		int rows = customerDaoImpl.addCustomer(customer);
		if (rows > 0) {
			int id = customerDaoImpl.getCustomerIdByNumber(customer.getPhone());
			return id;
		}
		return rows;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerDaoImpl.getAllCustomers();
		if (customers == null || customers.isEmpty()) {
			throw new RestaurantOperationException("No customers found");
		}
		return customers;
	}

	@Override
	public Customer getCustomerById(int id) {
		return customerDaoImpl.getCustomerById(id);
	}

}