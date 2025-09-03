package com.lms.web.controller;

import com.lms.web.model.Employee;
import com.lms.web.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

    @GetMapping("/getUserdetails")
    public Employee getEmployeeByEmail(@RequestParam String email) {
        return employeeService.getEmployeeByEmail(email);
    }
}