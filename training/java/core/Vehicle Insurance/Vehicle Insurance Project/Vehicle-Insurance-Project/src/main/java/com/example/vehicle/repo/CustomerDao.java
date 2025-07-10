package com.example.vehicle.repo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehicle.model.Customer;
import com.example.vehicle.rowMappers.CustomerRowMapper;

@Repository
@Transactional
public class CustomerDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CustomerDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String addCustomer(Customer customer) throws SQLException {
		String custAddSql = "Insert into customers(name,email,contact,gender,age,occupation,income,address,status,customer_updated_on,customer_updated_by,created_by)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println(customer.getGender().getVal().charAt(0));
		int rowsAffected = jdbcTemplate.update(custAddSql, customer.getName(), customer.getEmail(), customer.getContact(),
				customer.getGender().getVal(), customer.getAge(), customer.getOccupation(), customer.getIncome(),
				customer.getAddress(), "A", LocalDateTime.now(), customer.getCustomerUpdatedBy(),
				customer.getCreatedBy());

		if (rowsAffected == 0) {
			return "Customer not added";
		} else {
			return "Customer Added Successfully";
		}
	}
	
	public String updateCustomer(Customer customer) throws SQLException {
		System.out.println(Character.toString(customer.getStatus()));
		String custUpdateSql = "Update customers set name=?,email=?,contact=?,gender=?,age=?,occupation=?,income=?,address=?,status='A',customer_updated_on=?,customer_updated_by=?,created_by=? where customer_id=?";
		int rowsAffected = jdbcTemplate.update(custUpdateSql, customer.getName(), customer.getEmail(), customer.getContact(),
				customer.getGender().getVal(), customer.getAge(), customer.getOccupation(), customer.getIncome(),
				customer.getAddress(), customer.getCustomerUpdatedOn(), customer.getCustomerUpdatedBy(),
				customer.getCreatedBy(),customer.getCustomerId());

		if (rowsAffected == 0) {
			return "Customer not Updated";
		} else {
			return "Customer Updated Successfully";
		}
	}
	
	public String updateCustomerStatus(int customerId,char status) throws SQLException {
		String sql="Update customers set status=? where customer_id=?";
		int rowsAffected=jdbcTemplate.update(sql,Character.toString(status),customerId);
		if(rowsAffected==0) {
			return "Status not Updated,Check Your Customer Id";
		}
		else {
			return "Status updated Successfully";
		}
		
	}
	
	public Customer getCustomerById(int customerId) throws Exception {
		String sql="SELECT * FROM customers WHERE customer_id=?";
		Customer customer=null;
		try {
		customer=jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), customerId);
		}
		catch(Exception e) {
		    throw new Exception("No Customer found with CustomerId: " + customerId);
		}
		return customer;
	}
	
	public String deleteCustomerById(int customerId) throws Exception {
		String sql="Update customers set status='I' WHERE customer_id=?";
		int rowsAffected=jdbcTemplate.update(sql,customerId);
		if(rowsAffected==0) {
			rowsAffected=jdbcTemplate.update("delete rom users where customer_id=?",customerId);
			if(rowsAffected==0) {
				return "Failed to delete User details";
			}
			else {
				return "Failed to delete customer details";
			}
		}
		else {
			return "Customer Deleted Successfully";
		}		
	}
	
	public  List<Customer> getAllCustomers() throws SQLException {
		String sql="SELECT * FROM customers";
		return jdbcTemplate.query(sql,new CustomerRowMapper());	
	}	
}