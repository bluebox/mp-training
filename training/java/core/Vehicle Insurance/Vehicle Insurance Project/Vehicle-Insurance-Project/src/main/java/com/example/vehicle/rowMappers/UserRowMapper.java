package com.example.vehicle.rowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.vehicle.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user=new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setCustomerId(rs.getInt("customer_id"));
		user.setPasswordUpdatedBy(rs.getString("password_updated_by"));
		user.setPasswordUpdatedOn(rs.getTimestamp("password_updated_on").toLocalDateTime());
		return user;
	}
}
