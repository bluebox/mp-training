
public class Employee extends Worker{
	public long employeeId;
	public String hireDate;
	public Employee(String name,String birthDate,String endDate,long employeeId,String hireDate) {
		super(name,birthDate,endDate);
		this.employeeId=employeeId;
		this.hireDate=hireDate;
	}
}
