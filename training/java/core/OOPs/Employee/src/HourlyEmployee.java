import java.util.Scanner;

public class HourlyEmployee extends Employee{
	private double hourlyPayRate;
	Scanner sc=new Scanner(System.in);
	public HourlyEmployee(String name, String birthDate, String endDate, long employeeId, String hireDate,double hourlyPayRate) {
		super(name, birthDate, endDate, employeeId, hireDate);
		this.hourlyPayRate=hourlyPayRate;
	}
	public double getDoublePay() {
		System.out.println("The no of extra hours worked by "+name+" is:");
		return hourlyPayRate*sc.nextInt(); 
	}
	@Override
	public double collectPay() {
		return getDoublePay()+hourlyPayRate*(200);
	}
}
