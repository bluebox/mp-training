package com.rms.restaurant_management_system_backend.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.dao.CustomerDao;
import com.rms.restaurant_management_system_backend.domain.Customer;
import com.rms.restaurant_management_system_backend.exception.RestaurantOperationException;
import com.rms.restaurant_management_system_backend.rowmappers.CustomerRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private final JdbcTemplate jdbcTemplate;

	public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	private CustomerRowMapper customerRowMapper;

	@Override
	public int addCustomer(Customer customer) {
		return jdbcTemplate.update(SqlQueries.CUSTOMER_INSERT, customer.getName(), customer.getPhone());
	}

	@Override
	public List<Customer> getAllCustomers() {
		return jdbcTemplate.query(SqlQueries.GET_ALL_CUSTOMERS, customerRowMapper);
	}

	@Override
	public Customer getCustomerById(int id) {
		List<Customer> customer = jdbcTemplate.query(SqlQueries.GET_CUST_BY_ID,
				new BeanPropertyRowMapper<>(Customer.class), id);
		if (customer.isEmpty()) {
			throw new RestaurantOperationException("customer with id " + id + " not found");
		}
		return customer.get(0);
	}

	public Integer getCustomerIdByNumber(String phone) {
		return jdbcTemplate.queryForObject(SqlQueries.GET_ID_BY_PHONE, Integer.class, phone);
	}

}