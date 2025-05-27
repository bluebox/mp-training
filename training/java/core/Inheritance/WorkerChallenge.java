package Inheritance;

class Worker {
	String name;
	String birthDate;
	String endDate;

	public Worker(String name, String birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}

	public int getAge() {
		return 2025 - Integer.parseInt(birthDate.substring(0,4));

	}

	public double collectPay() {
		return 0.0;
	}

	public void terminate(String endDate) {
		this.endDate = endDate;
		System.out.println("Terminated on " + endDate);

	}
}

class Employee extends Worker {
	long employeeId;
	String hireDate;

	public Employee(String name, String birthDate, long employeeId, String hireDate) {
		super(name, birthDate);
		this.employeeId = employeeId;
		this.hireDate = hireDate;

	}
}

class SalariedEmployee extends Employee {
	double annualSalary;
	boolean isRetired;

	public SalariedEmployee(String name, String birthDate, long employeeId, String hireDate, double annualSalary) {
		super(name, birthDate, employeeId, hireDate);
		this.annualSalary = annualSalary;
		this.isRetired = false;

	}

	@Override
	public double collectPay() {
		return annualSalary / 26;
	}

	public void retire() {
		isRetired=true;
		System.out.println(name+" retired");
		
	}
}


public class WorkerChallenge {

	public static void main(String[] args) {
		
		SalariedEmployee emp=new SalariedEmployee("Chaitanya","2004-01-01",1234,"2025-05-21",234567.0);
		
		System.out.println("Age "+emp.getAge());
		System.out.println("Amount to collect"+emp.collectPay());
		emp.retire();
		emp.terminate("2025-05-30");
		
		
	}

}
