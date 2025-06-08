package com.service;
import java.util.ArrayList;
import java.util.List;

import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;

import com.domain.EmployeePojo;

public class EmployeeDataAnalytics {
	
	private  List<EmployeePojo> employeeList;
//	private EmployeePojo employeePojo;

	public EmployeeDataAnalytics() {
		this.employeeList= new ArrayList<>();
	}

	public EmployeePojo getEmployeePojo( String name) {
		
		return findEmployee(name);
	}

	public void setEmployeePojo(EmployeePojo employeePojo) {
		employeeList.add(employeePojo);
	}
	
	
	
	public EmployeePojo findEmployee( String name) {
		
		for(EmployeePojo employee :employeeList)
		{
			if(employee.getName() == name)
			{
				return employee;
			}
		}
		
		return null;
	}
public void printEmployees( ) {
		
		for(EmployeePojo employee :employeeList)
		{
			System.out.println(employee);
		}
		
	}

public List<EmployeePojo> getEmployeeList() {
	return employeeList;
}

	
	


}
