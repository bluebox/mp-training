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

public class Main {
	
	private final static EmployeeService employeeService=new EmployeeService();
    // Test main
    public static void main(String[] args) {
        String filePath = "C:\\Users\\mahip\\OneDrive\\Desktop\\medplus\\"
        		+ "mp-training\\training\\java\\core\\EmployeeProductivityAndAnalyticsSystem\\src\\com\\employee\\data.xlsx"; // change to your actual file path
        List<Employee> employeeList = employeeService.readEmployeesFromExcel(filePath);
        
        //System.out.println(employeeList);
//        for (Employee emp : employeeList) {
//            System.out.println(emp.getId() + " - " + emp.getName() + " - " + emp.getHoursWorked());
//        }
    }
}
