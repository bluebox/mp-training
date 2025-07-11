package com.app.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.app.model.Credentials;


public class CredentialsMapper implements RowMapper<Credentials> {
    public Credentials mapRow(ResultSet rs, int rowNum) throws SQLException {
        Credentials cred = new Credentials();
        cred.setUser_id(rs.getInt("user_id"));
        cred.setUserName(rs.getString("user_name"));
        cred.setPassword(rs.getString("password"));
        cred.setRole(rs.getString("role")); // new
        return cred;
    }
}