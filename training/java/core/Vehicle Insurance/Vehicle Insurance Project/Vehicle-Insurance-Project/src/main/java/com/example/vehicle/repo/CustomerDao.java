package com.example.vehicle.repo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehicle.model.Customer;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.rowMappers.CustomerRowMapper;

@Repository
@Transactional
public class CustomerDao {
	private final JdbcTemplate jdbcTemplate;
	@Autowired
	VehicleDao vehicleDao;

	@Autowired
	public CustomerDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addCustomer(Customer customer) throws SQLException {
		String custAddSql = "Insert into customers(name,email,contact,gender,age,occupation,income,address,status,customer_updated_on,customer_updated_by,created_by)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int rowsAffected = jdbcTemplate.update(custAddSql, customer.getName(), customer.getEmail(), customer.getContact(),
				customer.getGender().getVal(), customer.getAge(), customer.getOccupation(), customer.getIncome(),
				customer.getAddress(), "A",LocalDateTime.now(), customer.getCreatedBy(),
				customer.getCreatedBy());

		if (rowsAffected == 0) {
			return -1;
		} else {
			return jdbcTemplate.queryForObject("select customer_id from customers where email=?", Integer.class,customer.getEmail());
		}
	}
	
	public String updateCustomer(Customer customer) throws SQLException{
		if(jdbcTemplate.queryForObject("select status from customers where customer_id=?", Character.class,customer.getCustomerId())=='I') {
			return "There is no Active customer with this customer_id";
		}
		String custUpdateSql = "Update customers set name=?,email=?,contact=?,gender=?,age=?,occupation=?,income=?,address=?,status=?,"
				+ "customer_updated_on=?,customer_updated_by=? where customer_id=?";
		int rowsAffected = jdbcTemplate.update(custUpdateSql, customer.getName(), customer.getEmail(), customer.getContact(),
				customer.getGender().getVal(), customer.getAge(), customer.getOccupation(), customer.getIncome(),
				customer.getAddress(),"A", LocalDateTime.now(), customer.getCustomerUpdatedBy(),
				customer.getCustomerId());

		if (rowsAffected == 0) {
			return "Customer not Updated";
		} else {
			return "Customer Updated Successfully";
		}
	}
	
	public String updateCustomerStatus(int customerId,char status) throws SQLException{
		String sql="Update customers set status=? where customer_id=? and status='A'";
		int rowsAffected=jdbcTemplate.update(sql,String.valueOf(status),customerId);
		if(rowsAffected==0) {
			return "Status not Updated,Check Your Customer Id";
		}
		else {
			return "Status updated Successfully";
		}
		
	}
	
	public Customer getCustomerById(int customerId) throws Exception {
		String sql="SELECT customer_id,name,email,contact,gender,age,occupation,income,address,status,customer_updated_on,customer_updated_by,created_by FROM customers WHERE customer_id=?";
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
		String sql="Update customers set status='I' WHERE customer_id=? and status='A'";
		jdbcTemplate.update("delete from users where customer_id=?",customerId);
		List<Vehicle> vehicles=vehicleDao.getAllVehiclesByCustomer(customerId);
		for(Vehicle vehicle:vehicles) {
			vehicleDao.deleteVehicleById(vehicle.getVehicleId());
		}
		int rowsAffected=jdbcTemplate.update(sql,customerId);
		if(rowsAffected==0) {
			return "Customer not found";
		}
		else {
			return "Customer Deleted Successfully";
		}
		
	}
	
	
	
	public  List<Customer> getAllCustomers(){
		String sql="SELECT customer_id,name,email,contact,gender,age,occupation,income,address,status,customer_updated_on,customer_updated_by,created_by FROM customers";
		return jdbcTemplate.query(sql,new CustomerRowMapper());
		
	}	
}
