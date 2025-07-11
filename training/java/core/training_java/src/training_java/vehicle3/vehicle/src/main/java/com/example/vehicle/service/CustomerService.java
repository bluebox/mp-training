package com.example.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehicle.dao.CustomerDao;
import com.example.vehicle.model.Customer;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;

	public String addCustomer(Customer customer) throws Exception {
		try {
			customerDao.addCustomer(customer);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "Customer Added Successfully";
	}

	public String updateCustomer(Customer customer) throws Exception {
		try {
			customerDao.updateCustomer(customer);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "Customer Updated Successfully";

	}

	public String updateCustomerStatus(int customerId, char status) throws Exception {
		String result;
		try {
			result = customerDao.updateCustomerStatus(customerId, status);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}

	public String deleteCustomerById(int customerId) throws Exception {
		String result;
		try {
			result = customerDao.deleteCustomerById(customerId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return result;
	}

	public Customer getCustomerById(int customerId) throws Exception {
		Customer customer = customerDao.getCustomerById(customerId);
		return customer;
	}

	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

}
