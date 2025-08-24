package com.users.Users.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import org.springframework.jdbc.core.RowMapper;

import com.users.Users.model.UserRole;

public class UserRoleMapper implements RowMapper<UserRole> {

    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRole userRole = new UserRole();

        userRole.setUsername(rs.getString("user_code")); 
        userRole.setRoleName(rs.getString("role_name"));
        userRole.setUsernameString(rs.getString("usernameString"));
        
        userRole.setRoleId(Collections.singletonList(rs.getString("role_code")));

        userRole.setCountry(rs.getString("country"));
        userRole.setState(rs.getString("state"));
        userRole.setCity(rs.getString("city"));

        return userRole;
    }

}
