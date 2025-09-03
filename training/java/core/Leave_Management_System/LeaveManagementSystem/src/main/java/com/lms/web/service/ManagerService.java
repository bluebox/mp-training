package com.lms.web.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.lms.web.model.Employee;

public interface ManagerService {
	public List<Map<Integer,List<Map<Integer,Integer>>>> getAttendenceReport(int year,int managerId);
	
	 List<Employee> getTeamMembers(int managerId);

	    
	    public List<Map<String, Object>> getTeamExitRequests(int managerId);

	    List<Map<String, Object>> getPendingLeavesForTeam(int managerId);

	    List<Map<String, Object>> getTotalTeamAttendance(int managerId);

	    List<Map<String, Object>> getTeamAttendanceInRange(int managerId, Date from, Date to);
	    Employee getUserByEmail(String email);
}