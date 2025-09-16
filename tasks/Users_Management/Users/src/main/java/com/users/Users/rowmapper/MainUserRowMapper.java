package com.users.Users.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.users.Users.enums.Gender;
import com.users.Users.enums.UserStatus;
import com.users.Users.model.MainUser;


public class MainUserRowMapper implements RowMapper<MainUser>{

	@Override
	public MainUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		MainUser user = new MainUser(); 
		
	    user.setUserCode(rs.getString("user_code"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		if(rs.getString("gender")!=null) {
			user.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));			
		}else {
			user.setGender(null);
		}
		user.setPhoneNumber(rs.getString("phone_number"));
		user.setCountry(rs.getString("country"));
		user.setState(rs.getString("state"));
		user.setCity(rs.getString("city"));
		user.setPostalCode(rs.getString("postal_code"));
		String statusStr = rs.getString("status");
		if (statusStr != null) {
		    user.setStatus(UserStatus.valueOf(statusStr.toUpperCase()));
		} else {
		    user.setStatus(null);
		}
		user.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
		user.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
		
		return user;
	}
	
	

}
