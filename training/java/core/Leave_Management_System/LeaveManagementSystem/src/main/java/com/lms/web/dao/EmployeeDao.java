package com.lms.web.dao;

import java.util.List;

import com.lms.web.model.Employee;

public interface EmployeeDao {
	
	List<Employee> getEmployees();
	void addEmployee(Employee employee);
	List<Employee> getAssignedEmployees(int managerId);
	Employee getEmployeesById(int employeeId);
	Employee findByEmail(String email);
	List<Employee> getAllManagers();
}
