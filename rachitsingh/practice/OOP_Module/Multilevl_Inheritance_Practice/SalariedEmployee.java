package oop;

public class SalariedEmployee extends Employee{
	private double annualSalary;
	private boolean isRetired = false;
	
	public SalariedEmployee(String name, String birthDate, long employeeId, String hireDate, double annualSalary)
	{
		super(name, birthDate, employeeId, hireDate);
		this.annualSalary = annualSalary;
	}
	@Override
	public double collectPay()
	{
		return annualSalary/12; // monthly pay
	}
	public void retire()
	{
		isRetired = true;
		System.out.println(getName() + " has reached the retirement stage.");
	}
}