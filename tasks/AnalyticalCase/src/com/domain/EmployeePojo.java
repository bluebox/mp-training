package com.domain;

import java.sql.Date;

public class EmployeePojo {

	private String employeeId;
	private String name;
	private String department;
	private String projectId;
	private Date date;
	private String task;
	private double hoursWorked;
	private String remarks;
	
	public EmployeePojo(String employeeId, String name, String department, String projectId, Date date,
			String task, double hoursWorked, String remarks) {
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.projectId = projectId;
		this.date = date;
		this.task = task;
		this.hoursWorked = hoursWorked;
		this.remarks = remarks;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public String getProjectId() {
		return projectId;
	}

	public Date getDate() {
		return date;
	}

	public String getTask() {
		return task;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public String getRemarks() {
		return remarks;
	}

	@Override
	public String toString() {
		return this.name+ " "+this.department+" "+this.hoursWorked+" "+this.date;
	}
	
}
