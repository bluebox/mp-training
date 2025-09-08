package dev.kaushik.userManagement.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import dev.kaushik.userManagement.dao.RoleDao;
import dev.kaushik.userManagement.model.Role;
import dev.kaushik.userManagement.model.UserRole;
import dev.kaushik.userManagement.model.enums.Status;

@Repository
public class RoleDaoImpl implements RoleDao {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public RoleDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public boolean changeRoleStatus(int roleId) {
		Role currentRole = getRoles().stream().filter(role -> role.getRoleId() == roleId).findFirst().orElse(null);
		Status newStatus = currentRole.getStatus() == Status.ACTIVE ? Status.INACTIVE : Status.ACTIVE;
		String sql = "UPDATE roles SET status=:status, updated_by='ADMIN' WHERE roleId=:roleId";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("roleId", roleId)
				.addValue("status", String.valueOf(newStatus.getCode()));
		int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
		return rowsAffected > 0;
	}

	@Override
	public int addUserRole(UserRole userRole) {
		String sql = "INSERT INTO user_roles (userName, roleId, country, state, city) "
				+ "VALUES (:userName, :roleId, :country, :state, :city)";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("userName", userRole.getUserName())
				.addValue("roleId", userRole.getRoleId()).addValue("country", userRole.getCountry())
				.addValue("state", userRole.getState()).addValue("city", userRole.getCity());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] { "userRoleId" });
		return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
	}

	@Override
	public List<Role> getRoles() {
		String sql = "SELECT * FROM roles";
		RowMapper<Role> roleRowMapper = (rs, rowNum) -> {
			Role role = new Role();
			role.setRoleId(rs.getInt("roleId"));
			role.setRoleName(rs.getString("roleName"));
			role.setStatus(Status.fromCode(rs.getString("status").charAt(0)));
			role.setCreatedAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
			role.setCreatedBy(rs.getString("created_by"));
			role.setUpdatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
			role.setUpdatedBy(rs.getString("updated_by"));
			return role;
		};
		return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), roleRowMapper);
	}

	@Override
	public List<UserRole> getUserRoles() {
		String sql = "SELECT * FROM user_roles";
		RowMapper<UserRole> roleRowMapper = (rs, rowNum) -> {
			UserRole userRole = new UserRole();
			userRole.setUserRoleId(rs.getInt("userRoleId"));
			userRole.setRoleId(rs.getInt("roleId"));
			userRole.setUserName(rs.getString("userName"));
			userRole.setCountry(rs.getString("country"));
			userRole.setState(rs.getString("state"));
			userRole.setCity(rs.getString("city"));
			userRole.setCreatedAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
			userRole.setCreatedBy(rs.getString("created_by"));
			userRole.setUpdatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
			userRole.setUpdatedBy(rs.getString("updated_by"));
			return userRole;
		};
		return namedParameterJdbcTemplate.query(sql, roleRowMapper);
	}

	@Override
	public void deleteUserRoles(String userName) {
		String sql = "DELETE FROM user_roles WHERE userName=:userName";
		MapSqlParameterSource params = new MapSqlParameterSource("userName", userName);
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	@Override
	public boolean checkRolesConflict(int roleIdA, int roleIdB) {
		String sql = "select count(*) from conflicting_roles where roleIdA=:roleIdA and roleIdB=:roleIdB";
		RowMapper<Integer> countMapper = (rs, rowNum) -> rs.getInt(1);
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("roleIdA", roleIdA).addValue("roleIdB",
				roleIdB);
		int count = namedParameterJdbcTemplate.query(sql, params, countMapper).get(0);
		return count != 0;
	}

	@Override
	public List<String> getConflictingRoles() {
		RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString(1) + "," + rs.getString(2);
		String sql="SELECT * FROM conflicting_roles";
		return namedParameterJdbcTemplate.query(sql, rowMapper); 
	}

 }