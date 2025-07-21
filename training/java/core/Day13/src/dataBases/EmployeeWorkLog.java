package dataBases;

import java.time.LocalDate;
import java.util.Objects;

public class EmployeeWorkLog {
	
    private String employeeId;
    private String name;
    private String department;
    private String projectId;
    private LocalDate date;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;
        
	public EmployeeWorkLog(String employeeId, String name, String department, String projectId, LocalDate date,
			String taskCategory, double hoursWorked, String remarks) {
		
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.projectId = projectId;
		this.date = date;
		this.taskCategory = taskCategory;
		this.hoursWorked = hoursWorked;
		this.remarks = remarks;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(date, department, employeeId, hoursWorked, name, projectId, remarks, taskCategory);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeWorkLog other = (EmployeeWorkLog) obj;
		return Objects.equals(date, other.date) && Objects.equals(department, other.department)
				&& Objects.equals(employeeId, other.employeeId)
				&& Double.doubleToLongBits(hoursWorked) == Double.doubleToLongBits(other.hoursWorked)
				&& Objects.equals(name, other.name) && Objects.equals(projectId, other.projectId)
				&& Objects.equals(remarks, other.remarks) && Objects.equals(taskCategory, other.taskCategory);
	}


	@Override
	public String toString() {
		return "[employeeId=" + employeeId + ", name=" + name + ", department=" + department
				+ ", projectId=" + projectId + ", date=" + date + ", taskCategory=" + taskCategory + ", hoursWorked="
				+ hoursWorked + ", remarks=" + remarks + "]";
	}



	public String getEmployeeId() {
		return employeeId;
	}
	
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getDepartment() {
		return department;
	}
	
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	public String getProjectId() {
		return projectId;
	}
	
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
	public LocalDate getDate() {
		return date;
	}
	
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	public String getTaskCategory() {
		return taskCategory;
	}
	
	
	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}
	
	
	public double getHoursWorked() {
		return hoursWorked;
	}
	
	
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	
	
	public String getRemarks() {
		return remarks;
	}
	
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    
}
