package com.employee.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class EmployeeWorkLog {
    private String employeeId;
    private String name;
    private String department;
    private String projectId;
    private LocalDate date;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;
    private LocalTime time;

    public EmployeeWorkLog(String employeeId, String name, String department, String projectId, LocalDate date,
                           String taskCategory, double hoursWorked, String remarks, LocalTime time) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.projectId = projectId;
        this.date = date;
        this.taskCategory = taskCategory;
        this.hoursWorked = hoursWorked;
        this.remarks = remarks;
        this.time = time;
    }

    public EmployeeWorkLog(String employeeId, String name, String department, String projectId, LocalDate date,
                           String taskCategory, double hoursWorked, String remarks) {
        this(employeeId, name, department, projectId, date, taskCategory, hoursWorked, remarks, null);
    }

    public String getEmployeeId(){ 
    	return employeeId;
    }
    public void setEmployeeId(String employeeId) {
    	this.employeeId = employeeId; 
    }
    
    public String getName() {
    	return name; 
    }
    public void setName(String name) { 
    	this.name = name; 
    }
    
    public String getDepartment() { 
    	return department; 
    }
    public void setDepartment(String department) { 
    	this.department = department; 
    }
    
    public String getProjectId() { 
    	return projectId;
    }
    public void setProjectId(String projectId) { 
    	this.projectId = projectId; 
    }
    
    public LocalDate getDate() { 
    	return date; 
    }
    public void setDate(LocalDate date) { 
    	this.date = date;
    }
    
    public String getTaskCategory() { 
    	return taskCategory; 
    }
    public void setTaskCategory(String taskCategory) {
    	this.taskCategory = taskCategory; 
    }
    
    public double getHoursWorked() { 
    	return hoursWorked; 
    }
    public void setHoursWorked(double hoursWorked) { 
    	this.hoursWorked = hoursWorked;
    }
    
    public String getRemarks() { 
    	return remarks; 
    }
    public void setRemarks(String remarks) { 
    	this.remarks = remarks; 
    }
    
    public LocalTime getTime() {
    	return time; 
    }
    public void setTime(LocalTime time) { 
    	this.time = time;
    }

    @Override
    public String toString() {
        return "{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", projectId='" + projectId + '\'' +
                ", date=" + date +
                ", taskCategory='" + taskCategory + '\'' +
                ", hoursWorked=" + hoursWorked +
                ", remarks='" + remarks + '\'' +
                (time != null ? ", time=" + time : "") +
                '}';
    }

    public static String getTimePeriod(LocalTime time) {
        if (time == null) 
        	return "Unknown";
        if (time.isAfter(LocalTime.of(5, 59)) && time.isBefore(LocalTime.NOON)) 
        	return "Morning";
        else if (time.isBefore(LocalTime.of(18, 0))) 
        	return "Afternoon";
        else if (time.isBefore(LocalTime.of(22, 0))) 
        	return "Evening";
        else 
        	return "Other";
    }

}
