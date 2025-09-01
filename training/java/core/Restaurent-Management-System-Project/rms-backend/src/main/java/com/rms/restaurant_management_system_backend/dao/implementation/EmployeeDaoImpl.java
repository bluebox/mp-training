package com.rms.restaurant_management_system_backend.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rms.restaurant_management_system_backend.constant.EmployeeStatus;
import com.rms.restaurant_management_system_backend.dao.EmployeeDao;
import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.rowmappers.EmployeeRowMapper;
import com.rms.restaurant_management_system_backend.utilities.SqlQueries;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	private static final Logger log = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate template;

	@Autowired
	private EmployeeRowMapper employeeRowMapper;

	@Override
	public Employees addEmployee(Employees employee) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
//		jdbcTemplate.update(SqlQueries.EMPLOYEE_INSERT, employee.getName(), employee.getEmail(),
//				employee.getPhone(), employee.getStatus().getName(), employee.getDesignation().getName(),
//				employee.getJoin_date(), employee.getLeaving_date(), keyHolder);
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SqlQueries.EMPLOYEE_INSERT, new String[] { "id" });
				ps.setString(1, employee.getName());
				ps.setString(2, employee.getEmail());
				ps.setString(3, employee.getPhone());
				ps.setString(4, employee.getStatus().getName());
				ps.setString(5, employee.getDesignation().getName());
				ps.setDate(6, employee.getJoin_date());
				ps.setDate(7, employee.getLeaving_date());
				return ps;
			}
		}, keyHolder);
		employee.setEmpId(keyHolder.getKey().intValue());
		return employee;
	}

//	public int addEmployee(Employees employee) {
//
//		log.info("Adding new employee");
//		Map<String, Object> map = new HashMap<>();
//		map.put("name", employee.getName());
//		map.put("email", employee.getEmail());
//		map.put("phone", employee.getPhone());
//		map.put("status", employee.getStatus().getName());
//		map.put("designation", employee.getDesignation().getName());
//		map.put("join_date", employee.getJoin_date());
//		map.put("leaving_date", employee.getLeaving_date());
//		int rows = template.update(SqlQueries.EMPLOYEE_INSERT, map);
//		if (rows > 0) {
//			log.info("New employee added");
//		} else {
//			log.error("error while adding new employee");
//		}
//		return rows;

	// MapSqlParameterSource
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("name", employee.getName()).addValue("email", employee.getEmail())
//				.addValue("phone", employee.getPhone()).addValue("status", employee.getStatus().getName())
//				.addValue("designation", employee.getDesignation().getName())
//				.addValue("join_date", employee.getJoin_date()).addValue("leaving_date", employee.getLeaving_date());
//		return template.update(SqlQueries.EMPLOYEE_INSERT, params);

	// Using BeanPropertySqlParameterSource

//		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(employee);
//		return template.update(SqlQueries.EMPLOYEE_INSERT, params);
//	}

	@Override
	public List<Employees> getAllEmployeesss(Integer empId, String name, String email, String phone, Date startDate,
			Date endDate, List<EmployeeStatus> statuses) {
		Map<String, Object> params = new HashMap<>();

		params.put("hasEmpId", empId != null);
		params.put("hasEmpName", name != null && !name.isBlank());
		params.put("hasEmail", email != null && !email.isBlank());
		params.put("hasPhone", phone != null && !phone.isBlank());
		params.put("hasStartDate", startDate != null);
		params.put("hasEndDate", endDate != null);
		params.put("hasStatuses", statuses != null && !statuses.isEmpty());

		params.put("empId", empId);
		params.put("name", name);
		params.put("email", email);
		params.put("phone", phone);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("statuses",
				(statuses != null && !statuses.isEmpty()) ? statuses.stream().map(EmployeeStatus::getName).toList()
						: List.of("NO_STATUS"));

		return template.query(SqlQueries.EMPLOYEES, params, employeeRowMapper);
	}

	@Override
	public List<Employees> getAllEmployees() {
//		return template.query(SqlQueries.GET_ALL_EMPLOYEES, employeeRowMapper);
		return getAllEmployeesss(null, null, null, null, null, null, null);
	}

	@Override
	public Employees getEmpById(int id) {
		Map<String, Object> params = new HashMap<>();
		params.put("emp_id", id);
		Employees rows = template.query(SqlQueries.EMPLOYEE_BY_ID, params, employeeRowMapper).stream().findFirst()
				.orElse(null);
		return rows;
	}

	@Override
	public List<Employees> getActiveEmployees() {
		return template.query(SqlQueries.GET_ACTIVE_EMPLOYEES, employeeRowMapper);

	}

	@Override
	public int updateEmployee(Employees employee, int id) {
		employeeLog(id);
		Map<String, Object> map = new HashMap<>();
		map.put("name", employee.getName());
		map.put("email", employee.getEmail());
		map.put("phone", employee.getPhone());
		map.put("status", employee.getStatus().getName());
		map.put("designation", employee.getDesignation().getName());
		map.put("join_date", employee.getJoin_date());
		map.put("leaving_date", employee.getLeaving_date());
		map.put("emp_id", id);
		int rows = template.update(SqlQueries.UPDATE_EMPLOYEE, map);

		return rows;

	}

	@Override
	public int updateStatus(Employees employee, int id) {
		employeeLog(id);
		int rows = jdbcTemplate.update(SqlQueries.UPDATE_EMP_STATUS, employee.getStatus().getName(), id);

		return rows;

//		HashMap<String, Object> params = new HashMap<>();
//		params.put("emp_id", id);
//		params.put("status", employee.getStatus().getName());
//		return jdbcTemplate.update(SqlQueries.UPDATE_EMP_STATUS, params);

	}

	@Override
	public int deleteEmployee(int id) {
		employeeLog(id);
		Map<String, Object> params = new HashMap<>();
		params.put("emp_id", id);

		int rows = template.update(SqlQueries.DELETE_EMPLOYEE, params);

		return rows;
	}

	@Override
	public boolean selectByEmail(String email) {
		Integer count = jdbcTemplate.queryForObject(SqlQueries.EMPLOYEE_SELECT_BY_EMAIL, Integer.class, email);
		return count != null && count > 0;
	}

	@Override
	public boolean selectByMobile(String mobile) {
		Integer count = jdbcTemplate.queryForObject(SqlQueries.EMPLOYEE_SELECT_BY_MOBILE, Integer.class, mobile);
		return count != null && count > 0;
	}

	@Override
	public int getEmployeeIdByEmail(String email) {
		return jdbcTemplate.queryForObject(SqlQueries.GET_EMPID_BY_EMAIL, Integer.class, email);
	}

	@Override
	public int employeeLog(int empId) {

		return jdbcTemplate.update(SqlQueries.EMPLOYEE_LOG, empId);
	}

}
