package com.rms.restaurant_management_system_backend.service;

import java.util.List;

import com.rms.restaurant_management_system_backend.domain.Employees;

public interface EmployeeService {

	public Employees addEmployee(Employees employee);

	public List<Employees> getAllEmployees();

	public List<Employees> getActiveEmployees();

	public int updateEmployee(Employees employee, int id);

	public int updateEmpStatus(Employees employee, int id);

	int deleteEmployee(int id);

}
