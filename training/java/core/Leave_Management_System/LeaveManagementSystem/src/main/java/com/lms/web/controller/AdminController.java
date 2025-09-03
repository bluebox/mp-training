package com.lms.web.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.web.exceptions.ServiceException;
import com.lms.web.model.Employee;
import com.lms.web.model.ExitRequest;
import com.lms.web.model.LeaveRequest;
import com.lms.web.service.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/admin")
@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;

	@GetMapping("/getReport")
	public ResponseEntity<List<Map<Integer,List<Map<Integer,Integer>>>>> getYearlyAttendenceReport(@RequestParam("year") int year){
		List<Map<Integer,List<Map<Integer,Integer>>>> yearlyAttendenceReport= adminService.getAttendenceReport(year);
		return ResponseEntity.ok(yearlyAttendenceReport);
	}
	
	@GetMapping("/getManagers")
	public ResponseEntity<List<String>> getAllManagers(){
		List<String> managers = adminService.getAllManagers();
		return ResponseEntity.ok(managers);
	}
	
	@GetMapping("/getEmployeeLeaveRequestsReport")
	public ResponseEntity<Map<Employee,List<LeaveRequest>>> getAllEmployeesLeaveRequests(){
		Map<Employee, List<LeaveRequest>> allEmployeesLeaveRequests = null;
		try {
			allEmployeesLeaveRequests = adminService.getEmployeesLeaveRequests();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(allEmployeesLeaveRequests);
	}
	
	@GetMapping("/getEmployeeExitRequestsReport")
	public ResponseEntity<Map<Employee,List<ExitRequest>>> getAllEmployeesExitRequests(){
		Map<Employee, List<ExitRequest>> allEmployeesExitRequests = null;
		allEmployeesExitRequests = adminService.getEmployeesExitRequests();
		return ResponseEntity.ok(allEmployeesExitRequests);
	}
	
	@GetMapping("/getEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> employees = adminService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/getAllManagers")
	public ResponseEntity<List<Employee>> getManagers(){
		List<Employee> employees = adminService.getAllEmployees()
			    .stream()
			    .filter(managers -> managers.getEmployeeRole().equals("MANAGER"))
			    .collect(Collectors.toList());
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/getuserDetails")
	public ResponseEntity<Employee> getEmployeeDetails(@RequestParam("email") String email){
		Employee user=adminService.getUserByEmail(email);
		return ResponseEntity.ok(user);
	}
	
	 @GetMapping("/team-members")
	    public ResponseEntity<List<Map<String, Object>>> getTeamMembers(@RequestParam("managerId") int managerId) {
	        return ResponseEntity.ok(adminService.getTeamMembers(managerId));
	    }

}