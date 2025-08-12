package com.example.service;

import com.example.model.Employee;
import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
