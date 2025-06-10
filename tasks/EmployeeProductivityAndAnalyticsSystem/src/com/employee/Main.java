package com.employee;

import com.employee.domain.Employee;
import com.employee.service.EmployeeService;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

	private final static EmployeeService employeeService = new EmployeeService();

	public static void main(String[] args) {
		//String filePath = "/home//developer//Desktop//mp-training//training//java//core//EmployeeProductivityAndAnalyticsSystem//src//com//employee//data.xlsx";
		String filePath="C:\\Users\\mahip\\OneDrive\\desktop\\medplus\\mp-training\\tasks\\EmployeeProductivityAndAnalyticsSystem\\src\\com\\employee\\data.xlsx";
		employeeService.readEmployeesFromExcel(filePath);
//		employeeService.task1();
//		employeeService.task2();
		employeeService.task3();
	//	employeeService.task4();
	//	employeeService.task5();
	}
}
