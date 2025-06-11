import java.time.LocalDate;

public class EmployeeDetails {
    private String employeeId;
    private String name;
    private LocalDate date;
    private double hoursWorked;

    public EmployeeDetails(String employeeId, String name, 
                           LocalDate date,  double hoursWorked) {
        this.employeeId = employeeId;
        this.name = name;
        this.date = date;
        this.hoursWorked = hoursWorked;
    }
    public String getMonthKey() {
        return employeeId + "_" + date.getYear() + "-" + date.getMonthValue();
    }
    public String getDateKey() {
        return employeeId + "_" + date.toString();
    }
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public double getHoursWorked() { return hoursWorked; }
}