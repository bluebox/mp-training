import java.time.LocalDate;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Data Model
class EmployeeWorkLog {
    private String employeeId;
    private String name;
    private String department;
    private String projectId;
    private LocalDate date;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;

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

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public double getHoursWorked() { return hoursWorked; }
}
