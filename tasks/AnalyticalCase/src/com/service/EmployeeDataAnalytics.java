package com.service;
import java.util.ArrayList;
import java.util.List;

import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;

import com.domain.EmployeePojo;

public class EmployeeDataAnalytics {
	
	private  List<EmployeePojo> employeeList;

	public EmployeeDataAnalytics() {
		this.employeeList= new ArrayList<>();
	}

	
	public void setEmployeePojo(EmployeePojo employeePojo) {
		employeeList.add(employeePojo);
	}
	

public List<EmployeePojo> getEmployeeList() {
	return employeeList;
}

}
