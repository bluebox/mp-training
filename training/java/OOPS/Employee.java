
public class Employee extends Worker{
	private long empId;
	private String hireDate;
	
	Employee(String eName,String birthDate,long empId,String hireDate)
	{
		super(eName,birthDate);
		this.empId=empId;
		this.hireDate=hireDate;
		
	}
	Employee(long empId,String hireDate)
	{
		this.empId=empId;
		this.hireDate=hireDate;
	}
	

}
