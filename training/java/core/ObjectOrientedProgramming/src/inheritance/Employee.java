package inheritance;

public class Employee extends Worker{
	
	long employeeId;
	String hireDate;
	
	

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


	@Override
	public String toString() {
		return super.toString()+"Employee [employeeId=" + employeeId + ", hireDate=" + hireDate + "]";
	}

	
}
