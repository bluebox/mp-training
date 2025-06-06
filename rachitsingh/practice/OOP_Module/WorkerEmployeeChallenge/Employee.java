package WorkerEmployeeChallenge;

public class Employee extends Worker{
	private long employeeId;
	private String hireDate;
	
	public Employee(String name, String birthDate, long employeeId, String hireDate)
	{
		super(name, birthDate);
		this.employeeId = employeeId;
		this.hireDate = hireDate;
	}
}
