
import java.time.LocalDate;

public class Employee {
	
	private String name;
	private String employeeId;
	private String remarks;
	private LocalDate date;
	private double hoursWorked;
	private String projectId;
	private String taskCategory;
	private String department;
	public Employee() {
		name="Unknown";
		employeeId=null;
		remarks=null;
		date=null;
		hoursWorked=0;
		projectId=null;
		taskCategory=null;
		department=null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = LocalDate.parse(date);
	}
	public double getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(Double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTaskCategory() {
		return taskCategory;
	}
	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String toString() {
		return "EmployeeId: "+employeeId+" Name: "+name+" projectId: "+projectId+" taskCategory: "+taskCategory+" Department: "+department
				+" Date: "+date+" HoursWorked: "+hoursWorked+" Remarks: "+remarks;
	}
	
	
	}

