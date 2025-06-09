package casestudy2;

import java.time.LocalDate;

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

    // Constructor
    public EmployeeWorkLog(String employeeId, String name, String department, String projectId,
                           LocalDate date, String taskCategory, double hoursWorked, String remarks) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.projectId = projectId;
        this.date = date;
        this.taskCategory = taskCategory;
        this.hoursWorked = hoursWorked;
        this.remarks = remarks;
    }

    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getProjectId() { return projectId; }
    public LocalDate getDate() { return date; }
    public String getTaskCategory() { return taskCategory; }
    public double getHoursWorked() { return hoursWorked; }
    public String getRemarks() { return remarks; }

    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setTaskCategory(String taskCategory) { this.taskCategory = taskCategory; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
} 
