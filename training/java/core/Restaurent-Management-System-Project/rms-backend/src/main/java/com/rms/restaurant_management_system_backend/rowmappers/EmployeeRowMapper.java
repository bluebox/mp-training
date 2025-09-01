package com.rms.restaurant_management_system_backend.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import com.rms.restaurant_management_system_backend.constant.Designation;
import com.rms.restaurant_management_system_backend.constant.EmployeeStatus;
import com.rms.restaurant_management_system_backend.domain.Employees;

@Controller
public class EmployeeRowMapper implements RowMapper<Employees> {

	@Override
	public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {

		Employees employee = new Employees();
		employee.setEmpId(rs.getInt("emp_id"));
		employee.setName(rs.getString("name"));
		employee.setEmail(rs.getString("email"));
		employee.setPhone(rs.getString("phone"));
		employee.setStatus(EmployeeStatus.getEnumConstant(rs.getString("status")));
		employee.setDesignation(Designation.getEnumConstant(rs.getString("designation")));
		employee.setJoin_date(rs.getDate("join_date"));
		employee.setLeaving_date(rs.getDate("leaving_date"));

		return employee;
	}

}
