package com.medplus.Roles_backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medplus.Roles_backend.domain.RoleLocation;
import com.medplus.Roles_backend.domain.RoleResponse;
import com.medplus.Roles_backend.enums.ActiveStatus;
import com.medplus.Roles_backend.exception.UserValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class RoleDao implements RoleDaoInterface {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<String> getAvailableRoles(String userId) {
		String sql = """
				select r.role_name from roles r
				where r.status = :activeStatus and r.role_id not in (
				      select urp.role_id
				      from user_roles_places urp
				      where urp.user_id = :userId and urp.status = :activeStatus)
				""";

		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("activeStatus", ActiveStatus.ACTIVE.getCode());

		try {
			return namedParameterJdbcTemplate.queryForList(sql, params, String.class);
		} catch (Exception e) {
			throw new UserValidationException("Failed in getAvailableRoles dao");
		}
	}

	public List<String> getAssignedActiveRoles(String userId) {
		String sql = """
				select distinct r.role_name
				from user_roles_places urp
				join roles r ON urp.role_id = r.role_id
				where urp.user_id = :userId and urp.status = :activeStatus
				""";

		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("activeStatus", ActiveStatus.ACTIVE.getCode());

		try {
			return namedParameterJdbcTemplate.queryForList(sql, params, String.class);
		} catch (Exception e) {
			throw new UserValidationException("Failed in getting assigned active roles dao");
		}
	}

	public List<RoleResponse> getActiveRoleDetails(String userId) {
		String sql = """
				select r.role_name, p.country, p.state, p.city, urp.status
				from user_roles_places urp
				join roles r ON urp.role_id = r.role_id
				join places p ON urp.place_id = p.place_id
				where urp.user_id = :userId AND urp.status = :activeStatus
				""";

		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("activeStatus", ActiveStatus.ACTIVE.getCode());

		try {
			return namedParameterJdbcTemplate.query(sql, params, new RoleResponseRowMapper());
		} catch (Exception e) {
			throw new UserValidationException("Failed in getting active role details");
		}
	}

	private static class RoleResponseRowMapper implements RowMapper<RoleResponse> {
		@Override
		public RoleResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoleResponse response = new RoleResponse();
			response.setRole(rs.getString("role_name"));
			response.setCountry(rs.getString("country"));
			response.setState(rs.getString("state"));
			response.setCity(rs.getString("city"));
			response.setStatus(ActiveStatus.fromCode(rs.getString("status")));
			return response;
		}
	}

	public void assignRoles(String userId, List<RoleLocation> request) {
		try {
			for (RoleLocation rl : request) {
				Long placeId = getOrInsertPlace(rl.getCountry(), rl.getState(), rl.getCity());
				String roleId = getRoleIdByName(rl.getRole());

				String checkSql = "select count(urp_id) from user_roles_places where user_id=:userId and role_id=:roleId and place_id=:placeId";
				Map<String, Object> params = new HashMap<>();
				params.put("userId", userId);
				params.put("roleId", roleId);
				params.put("placeId", placeId);

				Integer count = namedParameterJdbcTemplate.queryForObject(checkSql, params, Integer.class);

				if (count != null && count > 0) {
					String updateSql = "update user_roles_places set status = :status, updated_time = NOW(), updated_by = :updatedBy where user_id=:userId and role_id=:roleId and place_id=:placeId";
					params.put("status", ActiveStatus.ACTIVE.getCode());
					params.put("updatedBy", "Admin");

					namedParameterJdbcTemplate.update(updateSql, params);
				} else {
					String insertSql = "insert into user_roles_places (user_id, role_id, place_id, status, created_by, created_time,updated_by,updated_time) values (:userId, :roleId, :placeId, :status, :createdBy, NOW(),:updatedBy,NOW())";
					params.put("status", ActiveStatus.ACTIVE.getCode());
					params.put("createdBy", "Admin");
					params.put("updatedBy", "Admin");

					namedParameterJdbcTemplate.update(insertSql, params);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserValidationException("Failed to assign roles");
		}
	}

	private Long getOrInsertPlace(String country, String state, String city) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("country", country);
			params.put("state", state);
			params.put("city", city);

			try {
				return namedParameterJdbcTemplate.queryForObject(
						"SELECT place_id FROM places WHERE country=:country AND state=:state AND city=:city", params,
						Long.class);
			} catch (EmptyResultDataAccessException e) {
				String insertSql = "INSERT INTO places (country, state, city) VALUES (:country, :state, :city)";
				namedParameterJdbcTemplate.update(insertSql, params);
				return getOrInsertPlace(country, state, city);
			}
		} catch (Exception e) {
			throw new UserValidationException("Failed to get or insert place");
		}
	}

	private String getRoleIdByName(String roleName) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("roleName", roleName);

			return namedParameterJdbcTemplate.queryForObject(
					"select role_id from roles where role_name=:roleName and status=:status",
					Map.of("roleName", roleName, "status", ActiveStatus.ACTIVE.getCode()), String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("Unknown or inactive role: " + roleName);
		} catch (Exception e) {
			throw new UserValidationException("Failed to fetch role ID for roleName: " + roleName);
		}
	}

	public void disableRolesByLocation(String userId, List<RoleLocation> items) {
		try {
			for (RoleLocation rl : items) {
				String roleId = getRoleIdByName(rl.getRole());
				Long placeId = getOrInsertPlace(rl.getCountry(), rl.getState(), rl.getCity());

				String sql = """
						update user_roles_places
						set status = :final_stat, updated_by = :updatedBy, updated_time = NOW()
						where user_id = :userId and role_id = :roleId and place_id = :placeId and status = :status
						""";

				Map<String, Object> params = new HashMap<>();
				params.put("final_stat", ActiveStatus.INACTIVE.getCode());
				params.put("updatedBy", "Admin");
				params.put("userId", userId);
				params.put("roleId", roleId);
				params.put("placeId", placeId);
				params.put("status", ActiveStatus.ACTIVE.getCode());

				namedParameterJdbcTemplate.update(sql, params);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			throw new UserValidationException("Failed to disable roles by location");
		}
	}

}
