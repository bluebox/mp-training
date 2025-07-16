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
                           String taskCategory, double hoursWorked, String remarks) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.projectId = projectId;
        this.date = date;
        this.taskCategory = taskCategory;
        this.hoursWorked = hoursWorked;
        this.remarks = remarks;
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

    public static String getTimePeriod(double hoursWorked) {
        if (hoursWorked <=5) {//morning 7 to 12
            return "Morning";
        } else if (hoursWorked > 5 && hoursWorked <=9) {//Afternoon 12 to 16
            return "Afternoon";
        } else if (hoursWorked >9 && hoursWorked<=13) {//Evening 16-20
            return "Evening";
        } else {
            return "OverTime";
        }
    }
    
    public String getHoursAndMinutes(double hoursWorked) {
    	int hrs=(int) hoursWorked;
    	double fraction_min=hoursWorked-hrs;
    	int min=(int)(fraction_min*100);
		while(min>59) {
			min-=60;
			hrs+=1; 
		} 
    	return ""+hrs+":"+min;
    }

}
