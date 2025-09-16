package com.users.Users.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import org.springframework.jdbc.core.RowMapper;

import com.users.Users.enums.UserAssignedRoleStatus;
import com.users.Users.model.UserRole;

public class UserRoleMapper implements RowMapper<UserRole> {

    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRole userRole = new UserRole();

        userRole.setUsercode(rs.getString("user_code")); 
        userRole.setRoleName(rs.getString("role_name"));
        userRole.setUsernameString(rs.getString("usernameString"));
        
        userRole.setRolecodes(Collections.singletonList(rs.getString("role_code")));

        userRole.setStatusString(UserAssignedRoleStatus.valueOf(rs.getString("status")));
        
        userRole.setCountry(rs.getString("country"));
        userRole.setState(rs.getString("state"));
        userRole.setCity(rs.getString("city"));
        userRole.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
		userRole.setUpdated_at(rs.getTimestamp("updated_at")!=null ? rs.getTimestamp("updated_at").toLocalDateTime() :null);
        return userRole;
    }

}
