package com.example.vehicle.repo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehicle.model.Vehicle;
import com.example.vehicle.rowMappers.VehicleRowMapper;

@Repository
@Transactional
public class VehicleDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public VehicleDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public String addVehicle(Vehicle vehicle) throws SQLException {
		String vehicleAddSql="Insert into vehicles(chasis_no,reg_num,vehicle_model,purchase_date,vehicle_updated_on,vehicle_updated_by,customer_id,created_by,status)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, 'A')";
		System.out.println(vehicle.getStatus());
		int rowsAffected=jdbcTemplate.update(vehicleAddSql,vehicle.getChasisNum(),vehicle.getRegNum(),vehicle.getVehicleModel(),
				LocalDateTime.now(),LocalDateTime.now(),vehicle.getCreatedBy(),vehicle.getCustomerId(),vehicle.getCreatedBy());
		if(rowsAffected==0) {
			return "Vehicle Not Added";
		}
		else {
			return "Vehicle Added Successfully";
		}
	}
	
//	public String updateVehicle(Vehicle vehicle) {
//		String vehicleUpdateSql="Update vehicles set chasis_no=?,reg_num=?,vehicle_model=?,purchase_date=?,vehicle_updated_on=?,vehicle_updated_by=?,"
//				+ "customer_id=?,created_by=? where vehicle_id=?";
//		int rowsAffected=jdbcTemplate.update(vehicleUpdateSql,vehicle.getChasisNum(),vehicle.getRegNum(),
//				vehicle.getVehicleModel(),vehicle.getPurchaseDate(),vehicle.getVehicleUpdatedOn(),vehicle.getVehicleUpdatedBy(),
//				vehicle.getCustomerId(),vehicle.getCreatedBy(),vehicle.getVehicleId());
//		if(rowsAffected==0) {
//			return "Vehicle Not Updated,Check your Vehicle id and Other Details";
//		}
//		else {
//			return "Vehicle Updated successfully";
//		}
//	}
//	
	public String updateVehicleNumber(String regNum,String updatedBy,LocalDateTime updatedOn,int vehicleId) throws SQLException {
		String sql="Update vehicles set reg_num=?,vehicle_updated_on=?,vehicle_updated_by=? where reg_num like 'temp%' and vehicle_id=?";
		int rowsAffected=jdbcTemplate.update(sql,regNum,updatedOn,updatedBy,vehicleId);
		if(rowsAffected==0) {
			return "Can't Update Vehicle Registration Number";
		}
		else {
			return "Registration Number Updated Successfully";
		}	
	}
	
	public Vehicle getVehicleById(int vehicleId) throws Exception {
		String sql="Select * from vehicles where vehicle_id=?";
		Vehicle vehicle=null;
		try {
			vehicle=jdbcTemplate.queryForObject(sql, new VehicleRowMapper(), vehicleId);
		}
		catch(Exception e) {
		    throw new Exception("No Vehicle found with VehicleId: " + vehicleId);
		}
		return vehicle;
	}
	
	public String deleteVehicleById(int vehicleId) throws Exception {
		String sql = "Update vehicles set status='I' where vehicle_id=?";
		String policySql = "Update policy set policy_status='I' where vehicle_id=?";

		int rowsAffected = jdbcTemplate.update(sql, vehicleId);
		if (rowsAffected == 0) {
			return "Vehicle Not Deleted ,error occurred";
		} else {
			int rowsAffected2 = jdbcTemplate.update(policySql, vehicleId);
			if (rowsAffected2 == 0) {
				return "Policy Not Deleted ,error occurred";
			} else {
				return "Vehicle Deleted Successfully";
			}
		}
	}
	
	public List<Vehicle> getAllVehicles() throws SQLException {
		String sql="Select * from vehicles";
		return jdbcTemplate.query(sql,new VehicleRowMapper());	
	}
	public List<Vehicle> getAllVehiclesByCustomer(int customerId) {
		return jdbcTemplate.query("select * from vehicles where customer_id=?", new VehicleRowMapper(),customerId);
	}
}
