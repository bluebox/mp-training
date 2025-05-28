package MultiInherit;

public class Employee extends Worker {
	long empId;
	String hireDate;
	Employee(String name,String birthDate,String endDate,long empId,String hireDate){
		super(name,birthDate,endDate);
		this.empId=empId;
		this.hireDate=hireDate;
	}

}
