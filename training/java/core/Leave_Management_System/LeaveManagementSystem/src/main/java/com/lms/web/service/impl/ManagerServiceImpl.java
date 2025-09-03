package com.lms.web.service.impl;

import com.lms.web.dao.AttendanceDAO;
import com.lms.web.dao.EmployeeDao;
import com.lms.web.dao.ManagerDao;
import com.lms.web.exceptions.ServiceException;
import com.lms.web.exceptions.ValidationException;
import com.lms.web.model.Employee;
import com.lms.web.service.ManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private AttendanceDAO attendenceDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ManagerDao managerDao; 

    @Override
//    @Transactional
    public List<Map<Integer,List<Map<Integer,Integer>>>> getAttendenceReport(int year,int managerId){
    	if(managerId==0 ) {
    		throw new ServiceException("NO manager found with id 0");
    	}
    	else if(year == 0) {
    		throw new ServiceException("Please provide year");
    	}
        List<Employee> employees= employeeDao.getAssignedEmployees(managerId);
        List<Map<Integer,List<Map<Integer,Integer>>>> yearlyAttendenceReport = new ArrayList<>();
        for(Employee employee: employees) {
            List<Map<Integer,Integer>> employeeYearlyWorkingdays=attendenceDao.getAllAttendenceByYear(year, employee.getEmployeeId());
            Map<Integer,List<Map<Integer,Integer>>> employeeYearlyReport = new HashMap<>();
            employeeYearlyReport.put(employee.getEmployeeId(), employeeYearlyWorkingdays);
            yearlyAttendenceReport.add(employeeYearlyReport);
        }
        return yearlyAttendenceReport;
    }


//    @Override
//    public List<Map<String, Object>> getTeamMembers(int managerId) {
//    	if(managerId==0) {
//    		throw new ServiceException("NO manager found with id 0");
//    	}
//        List<Employee> employees = employeeDao.getAssignedEmployees(managerId);
//        List<Map<String,Object>> result = new ArrayList<>();
//        for(Employee e : employees) {
//            Map<String,Object> map = new HashMap<>();
//            map.put("employeeId", e.getEmployeeId());
//            map.put("employeeName", e.getEmployeeName());
//            map.put("email", e.getEmployeeEmail());
//            map.put("role", e.getEmployeeRole());
//            map.put("mobile", e.getEmployeeMobileNo());
//            result.add(map);
//        }
//        return result;
//    }
    
    
    @Override
    public List<Employee> getTeamMembers(int managerId) {
    	if(managerId==0) {
    		throw new ServiceException("NO manager found with id 0");
    	}
        List<Employee> employees = employeeDao.getAssignedEmployees(managerId);
        return employees;
    }


    @Override
    public List<Map<String, Object>> getTeamExitRequests(int managerId) {
    	if(managerId==0) {
    		throw new ServiceException("NO manager found with id 0");
    	}
        return managerDao.getTeamExitRequests(managerId);
    }

    @Override
    public List<Map<String, Object>> getPendingLeavesForTeam(int managerId) {
    	if(managerId==0) {
    		throw new ServiceException("NO manager found with id 0");
    	}
        return managerDao.getPendingLeavesForTeam(managerId);
    }

    @Override
    public List<Map<String, Object>> getTotalTeamAttendance(int managerId) {
    	if(managerId==0) {
    		throw new ServiceException("NO manager found with id 0");
    	}
        return managerDao.getAllTeamAttendance(managerId);
    }

    @Override
    public List<Map<String, Object>> getTeamAttendanceInRange(int managerId, Date from, Date to) {
     	if(managerId==0 || from==null || to==null ) {
    		throw new ServiceException("NO manager found with id 0, from Date and to Date cannot be null");
    	}
     	
        return managerDao.getTeamAttendanceInRange(managerId, from, to);
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