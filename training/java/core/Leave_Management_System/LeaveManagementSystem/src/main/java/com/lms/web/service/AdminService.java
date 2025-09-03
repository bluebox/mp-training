package com.lms.web.service;

import java.util.List;
import java.util.Map;

import com.lms.web.exceptions.ServiceException;
import com.lms.web.model.Employee;
import com.lms.web.model.ExitRequest;
import com.lms.web.model.LeaveRequest;

public interface AdminService {
	List<Map<Integer,List<Map<Integer,Integer>>>> getAttendenceReport(int year);
	List<String> getAllManagers();
	Map<Employee,List<LeaveRequest>> getEmployeesLeaveRequests() throws ServiceException;
	Map<Employee,List<ExitRequest>> getEmployeesExitRequests();
	List<Employee> getAllEmployees();
	Employee getUserByEmail(String email);
	List<Map<String, Object>> getTeamMembers(int managerId);
	
}