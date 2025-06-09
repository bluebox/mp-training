import java.time.LocalDate;

public class EmployeeData {
    private String employeeId;
    private String name;
    private String department;
    private String projectId;
    private LocalDate date;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;
    public EmployeeData(String id,String name,String dept,String proId,LocalDate date,String task,double hoursWorked,String remarks){
        this.employeeId=id;
        this.name=name;
        this.date=date;
        this.department=dept;
        this.projectId=proId;
        this.taskCategory=task;
        this.hoursWorked=hoursWorked;
        this.remarks=remarks;
    }
    public String getEmployeeId() {
    	return this.employeeId;
    }
    public String getName() {
    	return this.name;
    }
    public String getDepartment() {
    	return this.getDepartment();
    }
    public String getProjectId() {
    	return this.projectId;
    }
    public LocalDate getDate() {
    	return this.date;
    }
    public String getTask() {
    	return this.taskCategory;
    }
    public double getHoursWorked() {
    	return this.hoursWorked;
    }
    public String getRemarks() {
    	return this.remarks;
    }
    @Override
    public String toString() {
    	return this.employeeId+" "+this.name+" "+this.department+" "+this.projectId+" "+this.date+" "+this.taskCategory+" "+this.remarks;
    }
}
