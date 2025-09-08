package com.medplus.Roles_backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medplus.Roles_backend.domain.ActiveMembers;
import com.medplus.Roles_backend.domain.BaseUser;
import com.medplus.Roles_backend.domain.UserRequest;
import com.medplus.Roles_backend.enums.ActiveStatus;
import com.medplus.Roles_backend.enums.ApprovalStatus;
import com.medplus.Roles_backend.enums.Gender;
import com.medplus.Roles_backend.exception.UserValidationException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRequestDao implements UserrequestDaoInterface {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int save(UserRequest request) {
		String sql = """
				insert into user_requests (
				  first_name, last_name, username, age, emp_id, mobile, email,
				  state, city, country, gender, active_status, approval_status,
				  created_by, updated_by, created_time, updated_time)
				   values (
				  :first_name, :last_name, :username, :age, :emp_id, :mobile, :email,
				  :state, :city, :country, :gender, :active_status, :approval_status,
				  :created_by, :updated_by, NOW(), NOW())
				""";

		Map<String, Object> params = new HashMap<>();
		params.put("first_name", request.getFirstName());
		params.put("last_name", request.getLastName());
		params.put("username", request.getUsername());
		params.put("age", request.getAge());
		params.put("emp_id", request.getEmpId());
		params.put("mobile", request.getMobile());
		params.put("email", request.getEmail());
		params.put("state", request.getState());
		params.put("city", request.getCity());
		params.put("country", request.getCountry());
		params.put("gender", request.getGender().getCode());
		params.put("active_status", request.getActiveStatus().getCode());
		params.put("approval_status", ApprovalStatus.PENDING.getCode());
		params.put("created_by", request.getCreatedBy());
		params.put("updated_by", request.getCreatedBy());

		try {
			return namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {
			throw new UserValidationException("Failed to save user request for username: " + request.getUsername());
		}
	}

	public List<UserRequest> getAllRequests() {
		String sql = """
				select req_id,first_name,last_name, username,age,gender,emp_id,mobile,email,state,
				       city,country,approval_status,active_status, created_time,updated_time, created_by,
				       updated_by FROM user_requests """;

		try {
			return namedParameterJdbcTemplate.query(sql, new UserRequestRowMapper());
		} catch (Exception e) {
			throw new UserValidationException("Failed to fetch all user requests");
		}
	}

	public class UserRequestRowMapper implements RowMapper<UserRequest> {
		@Override
		public UserRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserRequest ur = new UserRequest();
			ur.setReqId(rs.getLong("req_id"));
			BaseUserRowMapperHelper.mapBaseUser(rs, ur);
			return ur;
		}
	}

	public class BaseUserRowMapperHelper {

		public static void mapBaseUser(ResultSet rs, BaseUser user) throws SQLException {
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setUsername(rs.getString("username"));
			user.setAge(rs.getObject("age") != null ? rs.getInt("age") : null);
			user.setGender(Gender.fromCode(rs.getString("gender")));
			user.setEmpId(rs.getString("emp_id"));
			user.setMobile(rs.getString("mobile"));
			user.setEmail(rs.getString("email"));
			user.setState(rs.getString("state"));
			user.setCity(rs.getString("city"));
			user.setCountry(rs.getString("country"));
			user.setApprovalStatus(ApprovalStatus.fromCode(rs.getString("approval_status")));
			user.setActiveStatus(ActiveStatus.fromCode(rs.getString("active_status")));

			Timestamp created = rs.getTimestamp("created_time");
			user.setCreatedAt(created.toLocalDateTime());

			Timestamp updated = rs.getTimestamp("updated_time");
			user.setUpdatedAt(updated.toLocalDateTime());

			user.setCreatedBy(rs.getString("created_by"));
			user.setUpdatedBy(rs.getString("updated_by"));
		}
	}

	public List<ActiveMembers> findAllActiveUsers() {
		String sql = """
				select m.id, m.first_name, m.last_name, m.username, m.age, m.gender, m.emp_id,
				       m.mobile, m.email, m.state, m.city, m.country,
				       m.active_status, m.approval_status,
				       m.created_by, m.updated_by, m.created_time, m.updated_time,
				       GROUP_CONCAT(DISTINCT r.role_name SEPARATOR ',') AS roles
				from main_table m
				left join user_roles_places urp ON urp.user_id = m.id AND urp.status = :userRoleStatus
				left join roles r ON r.role_id = urp.role_id AND r.status = :roleStatus
				where m.active_status = :activeStatus
				GROUP BY m.id, m.first_name, m.last_name, m.username, m.age, m.gender, m.emp_id,
				         m.mobile, m.email, m.state, m.city, m.country,
				         m.active_status, m.approval_status,
				         m.created_by, m.updated_by, m.created_time, m.updated_time
				ORDER BY m.id
				""";
		
		Map<String, String> params = new HashMap<>();
		params.put("activeStatus", ActiveStatus.ACTIVE.getCode());
		params.put("userRoleStatus", ActiveStatus.ACTIVE.getCode());
		params.put("roleStatus", ActiveStatus.ACTIVE.getCode());


		try {
			return namedParameterJdbcTemplate.query(sql, params,new ActiveMembersRowMapper());
		} catch (Exception e) {
			throw new UserValidationException("Failed to fetch all active users");
		}
	}

	public class ActiveMembersRowMapper implements RowMapper<ActiveMembers> {
		@Override
		public ActiveMembers mapRow(ResultSet rs, int rowNum) throws SQLException {
			ActiveMembers u = new ActiveMembers();
			u.setUserId(rs.getString("id"));
			BaseUserRowMapperHelper.mapBaseUser(rs, u);

			String rolesStr = rs.getString("roles");
			u.setRoles(rolesStr == null || rolesStr.isBlank() ? Collections.emptyList()
					: Arrays.stream(rolesStr.split(",")).map(String::trim).collect(Collectors.toList()));
			return u;
		}
	}

	public UserRequest findById(Long reqId) {
		String sql = """
				select req_id,first_name,last_name, username,age,gender,emp_id,mobile,email,state,
				       city,country,approval_status,active_status, created_time,updated_time, created_by,
				       updated_by from user_requests where req_id = :req_id """;
		Map<String, Object> params = new HashMap<>();
		params.put("req_id", reqId);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, params, new UserRequestRowMapper());
		} catch (Exception e) {
			throw new UserValidationException("Failed to fetch user request with req_id: " + reqId);
		}
	}

	public void updateApprovalStatus(Long reqId, ApprovalStatus status) {
		String sql = "update user_requests set approval_status = :status where req_id = :req_id";
		Map<String, Object> params = new HashMap<>();
		params.put("status", status.getCode());
		params.put("req_id", reqId);
		try {
			namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {
			throw new UserValidationException("Failed to update approval status for req_id: " + reqId);
		}
	}

	public void insertIntoMainTable(UserRequest request, String encryptedPassword) {
		String sql = """
				insert into main_table (
				  first_name, last_name, username, password, age, gender, emp_id, mobile, email,
				  state, city, country, approval_status, active_status,
				  created_by, updated_by, created_time, updated_time
				) values (
				  :first_name, :last_name, :username, :password, :age, :gender, :emp_id, :mobile, :email,
				  :state, :city, :country, :approval_status, :active_status,
				  :created_by, :updated_by, NOW(), NOW()
				)
				""";

		Map<String, Object> params = new HashMap<>();
		params.put("first_name", request.getFirstName());
		params.put("last_name", request.getLastName());
		params.put("username", request.getUsername());
		params.put("password", encryptedPassword);
		params.put("age", request.getAge());
		params.put("gender", request.getGender().getCode());
		params.put("emp_id", request.getEmpId());
		params.put("mobile", request.getMobile());
		params.put("email", request.getEmail());
		params.put("state", request.getState());
		params.put("city", request.getCity());
		params.put("country", request.getCountry());
		params.put("approval_status", ApprovalStatus.APPROVED.getCode());
		params.put("active_status", request.getActiveStatus().getCode());
		params.put("created_by", request.getCreatedBy());
		params.put("updated_by", request.getUpdatedBy());

		try {
			namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {
			throw new UserValidationException("Failed to insert user into main_table for username: " + request.getUsername());
		}
	}

	public void updateActiveStatus(Long reqId, ActiveStatus status) {
		String sql = "update user_requests SET active_status = :status WHERE req_id = :req_id";
		Map<String, Object> params = new HashMap<>();
		params.put("status", status.getCode());
		params.put("req_id", reqId);
		try {
			namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {
			throw new UserValidationException("Failed to update active status for req_id: " + reqId);
		}
	}
}
