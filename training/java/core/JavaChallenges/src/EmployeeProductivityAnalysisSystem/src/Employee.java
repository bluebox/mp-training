
import java.time.LocalDate;

public class Employee {
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	String employeeId;
	String name;
	String department;
	String projectId;
	LocalDate date;
	String taskCategory;
	double hoursWorked;
	String remarks;
	public Employee(String employeeId,String name,String department,String projectId,LocalDate date,String taskCategory,double hoursWorked,String remarks){
		this.employeeId=employeeId;
		this.name=name;
		this.department=department;
		this.date=date;
		this.projectId=projectId;
		this.taskCategory=taskCategory;
		this.hoursWorked=hoursWorked;
		this.remarks=remarks;
	}
	public String toString() {
		System.out.println(this.department);
		return "Employee ID : "+this.employeeId+"\nName : "+this.name+"\nDepartment : "+this.department+"\nProject ID : "+this.projectId+"\nDate : "+this.date+"\nTask Category : "+this.taskCategory+"\nHours Worked : "+this.hoursWorked+"\nRemarks : "+this.remarks;
	}
}
