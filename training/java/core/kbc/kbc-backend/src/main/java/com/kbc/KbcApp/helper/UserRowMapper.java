package com.kbc.KbcApp.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.kbc.KbcApp.enums.RoleEnum;
import com.kbc.KbcApp.enums.StatusEnum;
import com.kbc.KbcApp.pojos.Users;

public class UserRowMapper implements RowMapper<Users> {

	@Override
	public Users mapRow(ResultSet rs, int agr1) throws SQLException {
		Users user = new Users();
		user.setUserId(rs.getInt("UserId"));
		user.setUsername(rs.getString("UserName"));
		user.setPassword(rs.getString("Password"));
		user.setRole(RoleEnum.valueOf(rs.getString("Role")));
		user.setStatus(StatusEnum.valueOf(rs.getString("Status")));
		user.setCreatedAt(rs.getObject("CreatedAt", LocalDateTime.class));
		user.setModifiedAt(rs.getObject("ModifiedAt", LocalDateTime.class));
		user.setModifiedBy(rs.getString("ModifiedBy"));
		return user;
	}
}
