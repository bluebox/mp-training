package com.demo.employeeelog;


	

	import java.time.LocalDate;

	public class EmployeeWorkLog {

	    private String employeeId;
	    private String name;
	    private String department;
	    private String projectId;
	    private LocalDate date;
	    private String taskCategory;
	    private double hoursWorked;
	    private String remarks;

	    // ✅ Constructor
	    public EmployeeWorkLog(String employeeId, String projectId, String department,
                LocalDate date, double hoursWorked, String taskCategory) {
this.employeeId = employeeId;
this.projectId = projectId;
this.department = department;
this.date = date;
this.hoursWorked = hoursWorked;
this.taskCategory = taskCategory;
}

	    

		// ✅ Getters
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

	    public LocalDate getDate() {
	        return date;
	    }

	    public String getTaskCategory() {
	        return taskCategory;
	    }

	    public double getHoursWorked() {
	        return hoursWorked;
	    }

	    public String getRemarks() {
	        return remarks;
	    }

	    // ✅ Setters
	    public void setEmployeeId(String employeeId) {
	        this.employeeId = employeeId;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setDepartment(String department) {
	        this.department = department;
	    }

	    public void setProjectId(String projectId) {
	        this.projectId = projectId;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

	    public void setTaskCategory(String taskCategory) {
	        this.taskCategory = taskCategory;
	    }

	    public void setHoursWorked(double hoursWorked) {
	        this.hoursWorked = hoursWorked;
	    }

	    public void setRemarks(String remarks) {
	        this.remarks = remarks;
	    }
	}



