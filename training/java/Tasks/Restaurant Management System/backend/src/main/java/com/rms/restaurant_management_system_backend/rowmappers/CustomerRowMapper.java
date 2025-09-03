package com.rms.restaurant_management_system_backend.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.rms.restaurant_management_system_backend.domain.Customer;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustId(rs.getInt("cust_Id"));
		customer.setName(rs.getString("name"));
		customer.setPhone(rs.getString("phone"));
		
		return customer;
		
	}
	

}
