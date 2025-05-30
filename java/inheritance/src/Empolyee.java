package inheritance;

public class Empolyee extends Worker{
	private long employeeId;
	private String hireDate;
	public Empolyee(String name, String birthYear, String endDate, long employeeId, String hireDate) {
	super(name, birthYear, endDate)	;
	this.employeeId=employeeId;
	this.hireDate=hireDate;
	}
	public long getEmployeeId() {
		return employeeId;
	}
}
