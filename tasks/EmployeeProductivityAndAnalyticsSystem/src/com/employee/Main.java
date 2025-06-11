package com.employee;

import java.util.List;
import java.util.Map;

import com.employee.domain.Employee;
import com.employee.service.DataManager;
import com.employee.service.EmployeeService;

public class Main {

	private final static EmployeeService employeeService = new EmployeeService();
	private final static DataManager data = new DataManager();

	public static void main(String[] args) {
		String filePath = "/home//developer//Desktop//mp-training//tasks//EmployeeProductivityAndAnalyticsSystem//src//com//employee//data.xlsx";
		// String
		// filePath="C:\\Users\\mahip\\OneDrive\\desktop\\medplus\\mp-training\\tasks\\EmployeeProductivityAndAnalyticsSystem\\src\\com\\employee\\data.xlsx";
		Map<String, List<Employee>> employees = data.readEmployeesFromExcel(filePath);
		employeeService.task1(employees);
		employeeService.task2(employees);
		employeeService.task3(employees);
		employeeService.task4(employees);
		employeeService.task5(employees);
	}
}
