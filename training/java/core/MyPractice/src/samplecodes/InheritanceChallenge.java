package samplecodes;

public class InheritanceChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SalariedEmployee sal= new SalariedEmployee("Sameer","17/06/2002","21/05/2027",true,10000);
		System.out.println("Employee Age is "+sal.getAge());
		System.out.println("Pay is "+sal.collectPay());
		
	}
}
class Worker{
	public String name;
	public String birthDate;
	public String endDate;
	public Worker(String name, String dob, String endDate) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.birthDate=dob;
		this.endDate=endDate;
	}
	public int getAge() {
		String todayDate="29/05/2025";
		int currentYear=Integer.parseInt(todayDate.substring(6));
		int birthYear=Integer.parseInt(birthDate.substring(6));
		return currentYear-birthYear;
	}
	public double collectPay() {
		return 100000.0d;
	}
	public void terminate(String endDate) {
		System.out.println("Terminated on "+endDate);
	}
}
class Employee extends Worker{
	public Employee(String name, String dob, String endDate) {
		// TODO Auto-generated constructor stub
		super(name,dob,endDate);
	}
	public long employeeId ;
	public String hireDate;
}
class SalariedEmployee extends Employee{
	public double annualSalary;
	public boolean isRetired;
	public SalariedEmployee(String name,String dob,String endDate,boolean isRetired,double annualSalary) {
		super(name,dob,endDate);
		this.isRetired=isRetired;
		this.annualSalary=annualSalary;
	}
	public void retire() {
		String todayDate="29/05/2025";
		if(endDate.equals(todayDate)) {
			System.out.println("Employee Should Retire");
		}
		else {
			System.out.println("Employee is not retired");
		}
	}
}