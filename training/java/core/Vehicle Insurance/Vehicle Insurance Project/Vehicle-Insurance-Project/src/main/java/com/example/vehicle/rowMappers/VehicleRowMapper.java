package com.example.vehicle.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.vehicle.model.Vehicle;

public class VehicleRowMapper implements RowMapper<Vehicle> {
	@Override
	public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
		Vehicle vehicle=new Vehicle();
		vehicle.setVehicleId(rs.getInt("vehicle_id"));
		vehicle.setChasisNum(rs.getString("chasis_no"));
		vehicle.setRegNum(rs.getString("reg_num"));
		vehicle.setVehicleModel(rs.getString("vehicle_model"));
		vehicle.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
		vehicle.setVehicleUpdatedOn(rs.getTimestamp("vehicle_updated_on").toLocalDateTime());
		vehicle.setStatus(rs.getString("status").charAt(0));
		vehicle.setVehicleUpdatedBy(rs.getString("vehicle_updated_by"));
		vehicle.setCreatedBy(rs.getString("created_by"));
		vehicle.setCustomerId(rs.getInt("customer_id"));
		return vehicle;

	}
}
