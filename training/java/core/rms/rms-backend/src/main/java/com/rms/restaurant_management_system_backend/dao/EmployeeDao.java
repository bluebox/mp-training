package com.rms.restaurant_management_system_backend.dao;

import java.sql.Date;
import java.util.List;

import com.rms.restaurant_management_system_backend.constant.EmployeeStatus;
import com.rms.restaurant_management_system_backend.domain.Employees;

public interface EmployeeDao {
	public Employees addEmployee(Employees employee);

	public List<Employees> getAllEmployees();

	public Employees getEmpById(int id);

	public List<Employees> getActiveEmployees();

	public int updateEmployee(Employees employee, int id);

	public int updateStatus(Employees employee, int id);

	public boolean selectByEmail(String email);

	public boolean selectByMobile(String mobile);

	public int getEmployeeIdByEmail(String email);

	int employeeLog(int memberId);

	int deleteEmployee(int id);

	List<Employees> getAllEmployeesss(Integer empId, String name, String email, String phone, Date startDate,
			Date endDate, List<EmployeeStatus> statuses);
}
