package com.users.Users.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.users.Users.model.MainUser;

public class UserPasswordRowMapper implements RowMapper<MainUser> {
    @Override
    public MainUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        MainUser user = new MainUser();
        user.setUserCode(rs.getString("user_code"));
        user.setPassword(rs.getString("password"));
        try {
            user.setUsername(rs.getString("username"));
        } catch (SQLException ignored) {
        	
        }
        return user;
    }
}
