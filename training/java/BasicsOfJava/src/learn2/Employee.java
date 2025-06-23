package learn2;

public class Employee extends Worker{
	private long emmployeeId;
	private String hireDate;
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public long getEmmployeeId() {
		return emmployeeId;
	}
	public void setEmmployeeId(long emmployeeId) {
		this.emmployeeId = emmployeeId;
	}
}
class SalariedEmployee extends Employee{
	double annualSalary;
	boolean isRetired;
	void retire() {
		System.out.println(this.getName() + "is retired " + this.isRetired);
	}
}
class HourlyEmployee extends Employee{
	private double hourlyPayRate;
	public void getDoublePay() {
		System.out.println(this.getName()+" is getting Double pay");
	}
	public double getHourlyPayRate() {
		return hourlyPayRate;
	}
	public void setHourlyPayRate(double hourlyPayRate) {
		this.hourlyPayRate = hourlyPayRate;
	}
}
