package InheritanceChallange;
public class Employee extends Worker {
	private long employeeId;
	private String hireDate;
	public Employee(String name, String birthDate, String endDate, long employeeId, String hireDate) {
		super(name, birthDate, endDate);
		this.employeeId = employeeId;
		this.hireDate = hireDate;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	
}
