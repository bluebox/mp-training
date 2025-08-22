package com.prana;
import java.time.LocalDate;

public class EmpWorkLog {
    private String employeeId;
    private String name;
    private String department;
    private String projectId;
    private LocalDate date;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;

    public EmpWorkLog(String employeeId, String name, String department,
                           String projectId, LocalDate date, String taskCategory,
                           double hoursWorked, String remarks) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.projectId = projectId;
        this.date = date;
        this.taskCategory = taskCategory;
        this.hoursWorked = hoursWorked;
        this.remarks = remarks;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getProjectId() { return projectId; }
    public LocalDate getDate() { return date; }
    public String getTaskCategory() { return taskCategory; }
    public double getHoursWorked() { return hoursWorked; }
    public String getRemarks() { return remarks; }
}
