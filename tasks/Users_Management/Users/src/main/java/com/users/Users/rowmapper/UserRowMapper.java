package com.users.Users.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.users.Users.enums.Gender;
import com.users.Users.enums.RequestStatus;
import com.users.Users.enums.UserStatus;
import com.users.Users.model.UserRequest;


public class UserRowMapper implements RowMapper<UserRequest>{

	@Override
	public UserRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserRequest user = new UserRequest(); 
		
		user.setRequestId(rs.getInt("request_id"));
		user.setUsername(rs.getString("username"));
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
		user.setStatus(UserStatus.valueOf(rs.getString("status").toUpperCase()));
		user.setAprovedStatus(RequestStatus.valueOf(rs.getString("approvedStatus").toUpperCase()));
		user.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
		user.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
		
		return user;
	}
	
	

}
