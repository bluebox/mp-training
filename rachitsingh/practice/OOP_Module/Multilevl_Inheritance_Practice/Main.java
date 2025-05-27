package oop;

public class Main {
	public static void main(String[] args)
	{
		SalariedEmployee emp = new SalariedEmployee("Mukesh Sharma", "1989", 891234, "2018-04-21", 2200000);
		System.out.println("Employee Name: " + emp.getName());
		System.out.println(emp.getName() + " is " + emp.getAge() + " years old.");
		System.out.println("Monthly salary : " + emp.collectPay());
		emp.terminate("2025-12-31");
		emp.retire();
	}
}