package com.rms.restaurant_management_system_backend.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rms.restaurant_management_system_backend.constant.WaiterAvailability;
import com.rms.restaurant_management_system_backend.custom_classes.WaiterDetailsSelector;

public class WaiterDetailsRowMapper implements RowMapper<WaiterDetailsSelector> {
	@Override
	public WaiterDetailsSelector mapRow(ResultSet rs, int rowNum) throws SQLException {
		String name=rs.getString("name");
		int waiterId=rs.getInt("wtr_id");
		int employeeId=rs.getInt("emp_id");
		WaiterAvailability availability=WaiterAvailability.fromDbName(rs.getString("availability"));
		return new WaiterDetailsSelector(name,waiterId,employeeId,availability);
	}
}
