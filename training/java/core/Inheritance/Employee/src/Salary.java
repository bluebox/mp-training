import java.time.LocalDate;
import java.sql.Date;

public class Salary extends Employee{
	private double annualSalary;
	private boolean isRetired;
	public Salary(String name,String birthDate,String endDate,long employeeId,String hireDate,double annualSalary,boolean isRetired) {
		super(name,birthDate,endDate,employeeId,hireDate);
		this.annualSalary=annualSalary;
		this.isRetired=isRetired;
	}
	public void retire() {
		if(LocalDate.parse(endDate).compareTo(LocalDate.now())>=0) {
			System.out.println("It is too early to be retired");
		}
		else {
			isRetired=true;
			System.out.println("Congratulations! and thanks for your work with us");
		}
	}
	@Override
	public double collectPay() {
		return annualSalary;
	}
}
