package day12.inheritencechallenge;
import java.time.LocalDate;
public class SalariedEmployee extends Employee{

	
	private double annualSalary;
	private boolean isRetired;
	
	SalariedEmployee(double annualSalary,boolean isRetired,long employeeId,String hireDate,String name,String birthDate,String endDate){
		super(employeeId,hireDate,name,birthDate,endDate);
		this.annualSalary=annualSalary;
		this.isRetired=isRetired;
	}
	public void retire() {
		isRetired=true;
		super.terminate(LocalDate.now().toString());
	}
}
