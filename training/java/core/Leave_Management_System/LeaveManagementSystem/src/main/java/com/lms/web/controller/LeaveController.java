package com.lms.web.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.web.config.HolidayConfig;
import com.lms.web.model.Leave;
import com.lms.web.model.LeaveRequest;
import com.lms.web.service.LeaveRequestService;
@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class LeaveController {

	@Autowired
    private LeaveRequestService leaveService;
    @Autowired
    private HolidayConfig holidayConfig;
    private Map<String, String> holidays;
    
    @PostMapping("/apply")
    public ResponseEntity<String> applyLeave(@RequestBody LeaveRequest request) {
        leaveService.applyLeave(request);
        return ResponseEntity.ok("Leave applied successfully");
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveLeave(@PathVariable int id) {
        leaveService.approveLeave(id);
        return ResponseEntity.ok("Leave approved successfully");
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<String> rejectLeave(@PathVariable int id, @RequestParam String reason) {
        leaveService.rejectLeave(id, reason);
        return ResponseEntity.ok("Leave rejected successfully");
    }
    
    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> rejectLeave(@PathVariable int id) {
        leaveService.cancelLeave(id);
        return ResponseEntity.ok("Leave cancelled successfully");
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getMyLeaves(@PathVariable int employeeId) {
//    	leaveService.getLeavesById(employeeId);
        return ResponseEntity.ok(leaveService.getLeavesById(employeeId));
    }

    @GetMapping("/holidays")
    public ResponseEntity<Map<String, LocalDate>> getHolidays() {
        return ResponseEntity.ok(holidayConfig.getHolidays());
    }
    @GetMapping("leaveBalance")
    public ResponseEntity<Leave> getLeaveBalence(@RequestParam("employeeId") int employeeId){
    	return ResponseEntity.ok(leaveService.getLeaveBalence(employeeId));
    }



}
