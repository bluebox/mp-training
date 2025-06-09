
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeWorkLog {
    private String employeeId;
    private String name;
    private String department;
    private String projectId;
    private Date date;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;

    public EmployeeWorkLog(String employeeId, String name, String department, String projectId, Date date, String taskCategory, double hoursWorked, String remarks) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.projectId = projectId;
        this.date = date;
        this.taskCategory = taskCategory;
        this.hoursWorked = hoursWorked;
        this.remarks = remarks;
    }

    // Getters and setters (optional, but recommended)
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getProjectId() { return projectId; }
    public Date getDate() { return date; }
    public String getTaskCategory() { return taskCategory; }
    public double getHoursWorked() { return hoursWorked; }
    public String getRemarks() { return remarks; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = (date != null) ? sdf.format(date) : "N/A";

        return String.format("%-10s %-15s %-15s %-10s %-12s %-15s %-12.2f %-10s",
            employeeId, name, department, projectId, dateStr, taskCategory, hoursWorked, remarks);
    }

}

