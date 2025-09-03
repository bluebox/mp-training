package com.lms.web.service;

import com.lms.web.model.Employee;

public interface EmployeeService {
    Employee getEmployeeByEmail(String email);
}