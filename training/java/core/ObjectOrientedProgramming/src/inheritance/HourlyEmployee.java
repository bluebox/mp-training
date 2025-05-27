package inheritance;
import java.util.Scanner;


public class HourlyEmployee extends Employee{
	
	private double hourlyPayRate;


	public HourlyEmployee(String name, String birthDate, String endDate, long employeeId, String hireDate,
			double hourlyPayRate) {
		super(name, birthDate, endDate, employeeId, hireDate);
		this.hourlyPayRate = hourlyPayRate;
	}


	public double getHourlyPayRate() {
		return hourlyPayRate;
	}


	public void setHourlyPayRate(double hourlyPayRate) {
		this.hourlyPayRate = hourlyPayRate;
	}
	
	
	@Override
	public double collectPay()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of hours worked:: ");
		double noOfHours = sc.nextInt();
		double pay = getDoublePay()?noOfHours*hourlyPayRate*2:noOfHours*hourlyPayRate;
		return pay;
	}

	public boolean getDoublePay()
	{
		String hireDate = super.getHireDate();
		int minYears = 4;
		int hireYear = Integer.parseInt(hireDate.split("-")[2]);
		
		if(2025-hireYear < minYears)
		{
			System.out.println(getEmployeeId()+" is Not Elegible for double pay");
			return false;
		}
		else
		{
			System.out.println(getEmployeeId()+" is Elegible for double pay");
		}
		return  true;
	}


	@Override
	public String toString() {
		return super.toString()+"HourlyEmployee [hourlyPayRate=" + hourlyPayRate + ", employeeId=" + employeeId + ", hireDate="
				+ hireDate + "]";
	}


	
	
	
	
	

}
