package com.users.Users.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.users.Users.model.Role;

public class RoleMapper implements RowMapper<Role> {

	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		
		role.setRoleId(rs.getInt("id"));
		role.setRolecode(rs.getString("role_code"));
		role.setStatus(rs.getString("status"));
		role.setRoleName(rs.getString("role_name"));
		role.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
		role.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
		
		return role;
	}
	
	

}
