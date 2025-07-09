package com.example.vehicle.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.vehicle.enums.Gender;
import com.example.vehicle.model.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {
	 @Override
	    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Customer customer = new Customer();
	        customer.setCustomerId(rs.getInt("customer_id"));
	        customer.setName(rs.getString("name"));
	        customer.setEmail(rs.getString("email"));
	        customer.setContact(rs.getString("contact"));
	        String genderChar=rs.getString("gender");
	        if("M".equalsIgnoreCase(genderChar)) {
	        	customer.setGender(Gender.MALE);
	        }
	        else {
	        	customer.setGender(Gender.FEMALE);
	        }
	        customer.setAge(rs.getInt("age"));
	        customer.setStatus(rs.getString("status").charAt(0));
	        customer.setAddress(rs.getString("address"));
	        customer.setOccupation(rs.getString("occupation"));
	        customer.setIncome(rs.getDouble("income"));
	        customer.setCreatedBy(rs.getString("created_by"));
	        customer.setCustomerUpdatedOn(rs.getTimestamp("customer_updated_on").toLocalDateTime());
	        customer.setCustomerUpdatedBy(rs.getString("customer_updated_by")); 	        
	        return customer;
	    }
}
