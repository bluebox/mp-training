package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.model.Credentials;

public class CredentialsMapper implements RowMapper<Credentials> {

	@Override
	public Credentials mapRow(ResultSet rs, int rowNum) throws SQLException {
		Credentials cred = new Credentials();
		cred.setUserId(rs.getInt("user_id"));
		cred.setUserName(rs.getString("user_name"));
		cred.setPassword(rs.getString("password"));
		cred.setRole(rs.getString("role"));
		return cred;
	}
}

