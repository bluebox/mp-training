package com.rms.restaurant_management_system_backend.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rms.restaurant_management_system_backend.constant.WaiterAvailability;
import com.rms.restaurant_management_system_backend.domain.Waiters;

public class WaiterRowMapper implements RowMapper<Waiters> {
	@Override
	public Waiters mapRow(ResultSet rs, int rowNum) throws SQLException {
		int waiterId = rs.getInt("wtr_id");
		int employeeId = rs.getInt("emp_id");
		WaiterAvailability availability = WaiterAvailability.fromDbName(rs.getString("availability"));
		System.out.println(availability);
		return new Waiters(waiterId, employeeId, availability);
	}
}
