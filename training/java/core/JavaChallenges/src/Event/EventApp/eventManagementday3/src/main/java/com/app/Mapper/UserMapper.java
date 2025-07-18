package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.enums.Gender;
import com.app.enums.UserStatus;
import com.app.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		user.setUserId(rs.getInt("user_id"));
		user.setName(rs.getString("name"));
		user.setPhnNumber(rs.getString("phn_number"));
		user.setEmail(rs.getString("email"));
		user.setRole(rs.getString("role"));
		String genderCode = rs.getString("gender");
		user.setGender(genderCode != null ? Gender.fromCode(genderCode) : null);

		String statusCode = rs.getString("status");
		user.setStatus(statusCode != null ? UserStatus.fromCode(statusCode) : null);
		

		user.setDept(rs.getString("dept"));
		return user;
	}
}

