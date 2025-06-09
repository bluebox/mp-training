package com.employee.domain;

import java.time.LocalDate;

public class Employee {
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", projectId=" + projectId
				+ ", date=" + date + ", taskCategory=" + taskCategory + ", hoursWorked=" + hoursWorked + ", remark="
				+ remark + "]\n";
	}

	private String id;
	private String name;
	private String department;
	private String projectId;
	private LocalDate date;
	private String taskCategory;
	private double hoursWorked;
	private String remark;

	public Employee(String id, String name, String department, String projectId, LocalDate date, String taskCategory,
			double hoursWorked, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.projectId = projectId;
		this.date = date;
		this.taskCategory = taskCategory;
		this.hoursWorked = hoursWorked;
		this.remark = remark;
	}

	public String getId() {
		return id;
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

	public LocalDate getDate() {
		return date;
	}

	public String getTaskCategory() {
		return taskCategory;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public String getRemark() {
		return remark;
	}

}
