package com.lms.web.controller;

import com.lms.web.model.Attendance;
import com.lms.web.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

	@Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestParam("employeeId") int employeeId) {
        attendanceService.punchAttendence(employeeId);
        return ResponseEntity.ok("Attendance updated successfully.");
    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployee(@PathVariable int employeeId) {
        List<Attendance> employeeAttendence = attendanceService.getAttendanceByEmployee(employeeId);
    	return ResponseEntity.ok(employeeAttendence);
    }
    @GetMapping("/{employeeId}/{date}")
    public ResponseEntity<Attendance> getAttendanceByEmployeeAndDate(
            @PathVariable int employeeId,
            @PathVariable String date) { 
        Date sqlDate = Date.valueOf(date);
        return ResponseEntity.ok(attendanceService.getAttendanceByEmployeeAndDate(employeeId, sqlDate));
    }
    @PutMapping("/{employeeId}/edit")
    public ResponseEntity<String> updateAttendanceByEmployee( @PathVariable int employeeId,
    		@RequestParam("date") String date,@RequestParam("status") String status){
    	Date sqlDate = Date.valueOf(date);
    	attendanceService.updateAttendence(employeeId, sqlDate, status);
    	return ResponseEntity.ok("Attendance updated successfully for employee "+employeeId);
    }

}
