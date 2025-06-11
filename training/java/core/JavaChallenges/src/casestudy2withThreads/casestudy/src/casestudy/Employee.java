package casestudy;

import java.time.LocalDate;

public class Employee {
    public String employeeId;
    public String name;
    public String department;
    public String projectId;
    public LocalDate date;
    public String taskCategory;
    public double hoursWorked;
    public String remarks;

    public Employee(String employeeId, String name, String department, String projectId,
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

    public String getEmployeeId() {
        return employeeId;
    }
}
