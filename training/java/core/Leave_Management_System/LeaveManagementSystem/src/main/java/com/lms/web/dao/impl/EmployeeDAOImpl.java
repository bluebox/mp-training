package com.lms.web.dao.impl;

import com.lms.web.dao.EmployeeDao;
import com.lms.web.exceptions.DAOException;
import com.lms.web.model.Employee;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements  EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Employee> mapper;

	public JdbcTemplate getTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public Employee findByEmail(String email) {
		String sql = "SELECT employeeId, employeeName, employeeEmail, employeeRole, employeeMobile_No, employeeDOB, employeeDOJ, employeeGender, employeeAddress, reporterId FROM employees WHERE employeeEmail = ?";
		try {
			mapper = (rs, rowNum) -> {
				Employee employee = new Employee(rs.getInt("employeeId"), rs.getString("employeeName"),
						rs.getString("employeeEmail"), rs.getString("employeeRole"), rs.getString("employeeMobile_No"),
						rs.getDate("employeeDOB").toLocalDate(), rs.getDate("employeeDOJ").toLocalDate(),
						rs.getString("employeeGender"), rs.getString("employeeAddress"), rs.getInt("reporterId"));
				return employee;
			};
			Employee employee = jdbcTemplate.queryForObject(sql,mapper, email);
			return employee;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public void addEmployee(Employee employee) {
		if(employee==null) {
			throw new DAOException("Inavalid Employee details");
		}
		String addEmployeeQuery = "INSERT INTO employees(employeeName, employeeEmail, employeeRole, employeeMobile_No, employeeDOB, employeeDOJ, employeeGender, employeeAddress, reporterId) VALUES(?,?,?,?,?,?,?,?,?)";
		int rowsAffected = jdbcTemplate.update(addEmployeeQuery, employee.getEmployeeName(), employee.getEmployeeEmail(),
				employee.getEmployeeRole(), employee.getEmployeeMobileNo(), employee.getEmployeeDOB(),
				employee.getEmployeeDOJ(), employee.getEmployeeGender(), employee.getEmployeeAddress(),
				employee.getReporterId());

		try {
			if (rowsAffected == 0) {
				throw new SQLException("SQL ERROR: Error in Adding new Employee");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public Employee getEmployeesById(int employeeId) {
		Employee employee = null;
		String getEmployeesList = "SELECT employeeId, employeeName, employeeEmail, employeeRole, employeeMobile_No, employeeDOB, employeeDOJ, employeeGender, employeeAddress, reporterId  FROM employees WHERE employeeId = ?";
		mapper = (rs, rowNum) -> {
			Employee employees = new Employee(rs.getInt("employeeId"), rs.getString("employeeName"),
					rs.getString("employeeEmail"), rs.getString("employeeRole"), rs.getString("employeeMobile_No"),
					rs.getDate("employeeDOB").toLocalDate(), rs.getDate("employeeDOJ").toLocalDate(),
					rs.getString("employeeGender"), rs.getString("employeeAddress"), rs.getInt("reporterId"));
			return employees;
		};
		try {
			employee = jdbcTemplate.queryForObject(getEmployeesList, mapper, employeeId);
			if (employee == null) {
				throw new SQLException("Error While Fetching Employees");
			}
		} catch (EmptyResultDataAccessException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return employee;
	}

	@Override
	public List<Employee> getEmployees() {
		String getEmployeesList = "SELECT employeeId, employeeName, employeeEmail, employeeRole, employeeMobile_No, employeeDOB, employeeDOJ, employeeGender, employeeAddress, reporterId  FROM employees";
		mapper = (rs, rowNum) -> {
			Employee employee = new Employee(rs.getInt("employeeId"), rs.getString("employeeName"),
					rs.getString("employeeEmail"), rs.getString("employeeRole"), rs.getString("employeeMobile_No"),
					rs.getDate("employeeDOB").toLocalDate(), rs.getDate("employeeDOJ").toLocalDate(),
					rs.getString("employeeGender"), rs.getString("employeeAddress"), rs.getInt("reporterId"));
			return employee;
		};
		List<Employee> employees = jdbcTemplate.query(getEmployeesList, mapper);
		try {
			if (employees == null) {
				throw new SQLException("Error While Fetching Employees");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return employees;
	}

	@Override
	public List<Employee> getAssignedEmployees(int managerId) {
		String getEmployeesList = "SELECT employeeId, employeeName, employeeEmail, employeeRole, employeeMobile_No, employeeDOB, employeeDOJ, employeeGender, employeeAddress, reporterId FROM employees WHERE reporterId =?" ;
		mapper = (rs, rowNum) -> {
			Employee employee = new Employee(rs.getInt("employeeId"), rs.getString("employeeName"),
					rs.getString("employeeEmail"), rs.getString("employeeRole"), rs.getString("employeeMobile_No"),
					rs.getDate("employeeDOB").toLocalDate(), rs.getDate("employeeDOJ").toLocalDate(),
					rs.getString("employeeGender"), rs.getString("employeeAddress"), rs.getInt("reporterId"));
			return employee;
		};
		List<Employee> employees = jdbcTemplate.query(getEmployeesList, mapper,managerId);
		try {
			if (employees == null) {
				throw new SQLException("Error While Fetching Assigned Employees");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return employees;
	}
	
	@Override
	public List<Employee> getAllManagers(){
		String getManagersList="SELECT employeeId, employeeName, employeeEmail, employeeRole, employeeMobile_No, employeeDOB, employeeDOJ, employeeGender, employeeAddress, reporterId FROM employees WHERE employeeRole = 'manager'";
		mapper = (rs, rowNum) -> {
			Employee employee = new Employee(
					rs.getInt("employeeId")
					,rs.getString("employeeName")
					,rs.getString("employeeEmail")
					,rs.getString("employeeRole")
					,rs.getString("employeeMobile_No")
					,rs.getDate("employeeDOB").toLocalDate()
					,rs.getDate("employeeDOJ").toLocalDate()
					,rs.getString("employeeGender")
					,rs.getString("employeeAddress")
					,rs.getInt("reporterId")
					);
			return employee;
		};
		List<Employee> managers = jdbcTemplate.query(getManagersList, mapper);
		return managers;
	}
}
