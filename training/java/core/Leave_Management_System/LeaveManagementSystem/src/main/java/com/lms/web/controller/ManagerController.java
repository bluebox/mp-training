package com.lms.web.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.web.model.Employee;
import com.lms.web.service.ManagerService;
@RestController
@RequestMapping("/api/manager")
@CrossOrigin(origins = { "http://localhost:3000" }, allowedHeaders = "*", allowCredentials = "true")

public class ManagerController {
	@Autowired
	private ManagerService managerService;


	@GetMapping("/getAssinedEmployeeReport")
	public ResponseEntity<List<Map<Integer,List<Map<Integer,Integer>>>>> getYearlyAttendenceReport(@RequestParam("year") int year, @RequestParam("employeeId") int employeeId){
		List<Map<Integer,List<Map<Integer,Integer>>>> yearlyAttendenceReport= managerService.getAttendenceReport(year, employeeId);
		return ResponseEntity.ok(yearlyAttendenceReport);
	}
	
	 @GetMapping("/team-members")
	    public ResponseEntity<List<Employee>> getTeamMembers(
	            @RequestParam("managerId") int managerId) {

	        return ResponseEntity.ok(managerService.getTeamMembers(managerId));
	    }


	    @GetMapping("/exit-requests")
	    public ResponseEntity<List<Map<String, Object>>> getTeamExitRequests(
	            @RequestParam("managerId") int managerId)
 {

	        return ResponseEntity.ok(managerService.getTeamExitRequests(managerId));
	    }


	    @GetMapping("/leaves/pending")
	    public ResponseEntity<List<Map<String, Object>>> getPendingLeavesForTeam(
	            @RequestParam("managerId") int managerId) {

	        return ResponseEntity.ok(managerService.getPendingLeavesForTeam(managerId));
	    }


	    @GetMapping("/totalAttendence")
	    public ResponseEntity<List<Map<String, Object>>> getTeamAttendanceForDate(
	            @RequestParam("managerId") int managerId) {

	       
	        return ResponseEntity.ok(managerService.getTotalTeamAttendance(managerId));
	    }
	    
		@GetMapping("/getuserDetails")
		public ResponseEntity<Employee> getEmployeeDetails(@RequestParam("email") String email){
			Employee user=managerService.getUserByEmail(email);
			return ResponseEntity.ok(user);
		}

	
}