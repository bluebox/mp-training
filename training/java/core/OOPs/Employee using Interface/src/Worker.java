import java.time.LocalDate;
import java.time.Period;

public class Worker extends Employee implements Salary,HourlyEmployee{
	public String name;
	public String birthDate;
	public String endDate;
	private double annualSalary;
	private double hourlyPayRate;
	private int hours;
	private boolean isRetired;
	public Worker(String name,String birthDate,String endDate,long employeeId, String hireDate,double annualSalary,double hourlyPayRate,int hours,boolean isRetired) {
		super(employeeId, hireDate);
		this.name=name;
		this.birthDate=birthDate;
		this.endDate=endDate;
		this.annualSalary=annualSalary;
		this.hourlyPayRate=hourlyPayRate;
		this.hours=hours;
		this.isRetired=isRetired;
	}
	public int getAge() {
		LocalDate d=LocalDate.parse(birthDate);
		Period x = Period.between(d,LocalDate.now());
		return x.getYears();
	}
	public double collectPay() {
		return annualSalary+getDoublePay(hourlyPayRate,hours);
	}
	public void terminate() {
		System.out.println("Thanks "+name+" for your valuable services. But I am sorry to say this that you are terminated");
		
	}
	public void isRetire() {
		retire(endDate,isRetired);
	}
}
