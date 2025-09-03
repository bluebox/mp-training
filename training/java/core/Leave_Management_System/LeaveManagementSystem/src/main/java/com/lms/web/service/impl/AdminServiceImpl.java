package com.lms.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.web.dao.AttendanceDAO;
import com.lms.web.dao.EmployeeDao;
import com.lms.web.dao.ExitRequestDao;
import com.lms.web.dao.LeaveRequestsDao;
import com.lms.web.exceptions.ServiceException;
import com.lms.web.exceptions.ValidationException;
import com.lms.web.model.Employee;
import com.lms.web.model.ExitRequest;
import com.lms.web.model.LeaveRequest;
import com.lms.web.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AttendanceDAO attendenceDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ExitRequestDao exitRequestDao;
	@Autowired
	private LeaveRequestsDao leaveRequestsDao;
	
	@Override
	@Transactional
	public List<Map<Integer,List<Map<Integer,Integer>>>> getAttendenceReport(int year){
		if(year==0) {
			 throw new ValidationException("invalid year");
		}
		
		List<Employee> employees= employeeDao.getEmployees();
		List<Map<Integer,List<Map<Integer,Integer>>>> yearlyAttendenceReport = new ArrayList<>();
		for(Employee employee: employees) {
			List<Map<Integer,Integer>> employeeYearlyWorkingdays=attendenceDao.getAllAttendenceByYear(year, employee.getEmployeeId());
			Map<Integer,List<Map<Integer,Integer>>> employeeYearlyReport = new HashMap<>();
			employeeYearlyReport.put(employee.getEmployeeId(), employeeYearlyWorkingdays);
			yearlyAttendenceReport.add(employeeYearlyReport);
		}
		return yearlyAttendenceReport;
	}
	
	@Override
	public List<Employee> getAllEmployees(){
		List<Employee> employees= employeeDao.getEmployees();
		return employees;
	}
	
	@Override
	public List<String> getAllManagers(){
		List<String> managers=null;
		List<Employee> employees= employeeDao.getAllManagers();
		for(Employee employee:employees) {
			managers.add(employee.getEmployeeId()+". "+employee.getEmployeeName());
		}
		return managers;
	}
	
	@Override
	@Transactional
	public Map<Employee,List<LeaveRequest>> getEmployeesLeaveRequests() throws ServiceException{
		Map<Employee,List<LeaveRequest>> getEmployeesLeaves = new HashMap<>();
		List<Integer> exitEmployees = exitRequestDao.getexitEmployeeIds();
		List<Employee> allEmployees = employeeDao.getEmployees();
		List<LeaveRequest> leaveRequests;
		for(Employee employee:allEmployees) {
			if(!(exitEmployees.contains(employee.getEmployeeId()))) {
				leaveRequests = null;
				try {
					leaveRequests = leaveRequestsDao.getLeaveRequestsByEmployeeId(employee.getEmployeeId());
					getEmployeesLeaves.put(employee, leaveRequests);
				}
				catch( Exception e) {
					throw new ServiceException(e.getMessage());
				}
			}
		}
		return getEmployeesLeaves;
	}
	
	@Override
	@Transactional
	public Map<Employee,List<ExitRequest>> getEmployeesExitRequests(){
		Map<Employee,List<ExitRequest>> getEmployeesExitRequests = new HashMap<>();
		List<Integer> exitEmployees = exitRequestDao.getexitEmployeeIds();
		List<Employee> allEmployees = employeeDao.getEmployees();
		List<ExitRequest> exitRequests;
		for(Employee employee:allEmployees) {
			if(!(exitEmployees.contains(employee.getEmployeeId()))) {
				exitRequests = null;
				exitRequests = exitRequestDao.findExitRequests(Optional.of(employee.getEmployeeId()));
				getEmployeesExitRequests.put(employee, exitRequests);	
			}
		}
		return getEmployeesExitRequests;
	}
	
	@Override
    public List<Map<String, Object>> getTeamMembers(int managerId) {
    	if(managerId==0) {
    		throw new ServiceException("NO manager found with id 0");
    	}
        List<Employee> employees = employeeDao.getAssignedEmployees(managerId);
        List<Map<String,Object>> result = new ArrayList<>();
        for(Employee e : employees) {
            Map<String,Object> map = new HashMap<>();
            map.put("employeeId", e.getEmployeeId());
            map.put("employeeName", e.getEmployeeName());
            map.put("email", e.getEmployeeEmail());
            map.put("role", e.getEmployeeRole());
            map.put("mobile", e.getEmployeeMobileNo());
            result.add(map);
        }
        return result;
    }

	
	@Override
	public Employee getUserByEmail(String email) {
		if(email==null) {
			throw new ValidationException("Email cannot be null");
		}
		Employee user = employeeDao.findByEmail(email);
		return user;
	}
}