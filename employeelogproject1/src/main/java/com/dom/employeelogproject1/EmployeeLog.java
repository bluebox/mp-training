package com.dom.employeelogproject1;

import java.time.LocalDate;

public class EmployeeLog {

    private String employeeId;
    private String name;
    private String department;
    private String projectId;
    private LocalDate date;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;

    public EmployeeLog(LocalDate date, String department, String employeeId, double hoursWorked,
                       String name, String projectId, String remarks, String taskCategory) {
        this.date = date;
        this.department = department;
        this.employeeId = employeeId;
        this.hoursWorked = hoursWorked;
        this.name = name;
        this.projectId = projectId;
        this.remarks = remarks;
        this.taskCategory = taskCategory;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getProjectId() { return projectId; }
    public LocalDate getDate() { return date; }
    public String getTaskCategory() { return taskCategory; }
    public double getHoursWorked() { return hoursWorked; }
    public String getRemarks() { return remarks; }

    @Override
    public String toString() {
        return employeeId + " | " + name + " | " + department + " | " + projectId + " | " +
               date + " | " + taskCategory + " | " + hoursWorked + " | " + remarks;
    }
}
