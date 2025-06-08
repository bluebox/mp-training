package day12.inheritencechallenge;

public class Employee extends Worker{
	
	
	
	private long employeeId;
	private String hireDate;
	
	Employee(long employeeId,String hireDate,String name,String birthDate,String endDate){
		super(name,birthDate,endDate);
		this.employeeId=employeeId;
		this.hireDate=hireDate;
	}
	public String getEmplymentDuration(){
		return AgeCalculator.calculateAge(hireDate);
	}

}
