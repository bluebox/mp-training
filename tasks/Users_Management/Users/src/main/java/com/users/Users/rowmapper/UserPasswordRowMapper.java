package com.users.Users.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.users.Users.model.User;

public class UserPasswordRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserCode(rs.getString("user_code"));
        user.setPassword(rs.getString("password"));
        try {
            user.setUsername(rs.getString("username"));
        } catch (SQLException ignored) {
        	
        }
        return user;
    }
}
