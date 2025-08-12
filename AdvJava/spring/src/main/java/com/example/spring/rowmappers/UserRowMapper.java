package com.example.spring.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.spring.model.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
	
		user.setName(rs.getString("NAME"));
		user.setAge(Integer.parseInt(rs.getString("AGE")));
		user.setPnum(rs.getString("PNUM"));
		user.setEmail(rs.getString("EMAIL"));
		
		
		return user;
	}

}
