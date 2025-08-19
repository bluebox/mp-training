package dev.tulasidhar.case_study_2_excelfilereading.model;

import java.time.LocalDateTime;

public class EmployeeWorkLog {
    private String employeeId;
    private String name;
    private String department;
    private String projectId;
    private LocalDateTime dateTime;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;

    public EmployeeWorkLog(String employeeId, String name, String department, String projectId,
                           LocalDateTime dateTime, String taskCategory, double hoursWorked, String remarks) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.projectId = projectId;
        this.dateTime = dateTime;
        this.taskCategory = taskCategory;
        this.hoursWorked = hoursWorked;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "EmployeeWorkLog [employeeId=" + employeeId + ", name=" + name + ", department=" + department
                + ", projectId=" + projectId + ", dateTime=" + dateTime + ", taskCategory=" + taskCategory 
                + ", hoursWorked=" + hoursWorked + ", remarks=" + remarks + "]";
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    // Convenience methods for date-only operations
    public int getMonthValue() { return dateTime.getMonthValue(); }
    public int getDayOfMonth() { return dateTime.getDayOfMonth(); }
    public int getHour() { return dateTime.getHour(); }

    public String getTaskCategory() { return taskCategory; }
    public void setTaskCategory(String taskCategory) { this.taskCategory = taskCategory; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
