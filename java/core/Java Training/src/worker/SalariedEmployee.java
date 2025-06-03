package worker;

public class SalariedEmployee extends Employee {
	private double annualSalary;
	private boolean isRetired;
	
	public SalariedEmployee(String name,String birthDate,long employeeId,String hireDate,double annualSalary,boolean isRetired) {
		super(name,birthDate,employeeId,hireDate);
		this.annualSalary=annualSalary;
		this.isRetired=isRetired;
	}
	
	public boolean getIsRetired() {
		return isRetired;
	}
	public boolean retire() {
		if(isRetired) {
			System.out.println("Yes "+getName()+", he/she was Retired.");
		}
		else {
			System.out.println("No "+getName()+" he/she not Retire.");
		}
		return isRetired;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SalariedEmployee obj=new SalariedEmployee("Renu","2002",(long) 247.0,"21-05-2025",600000,false);
		System.out.println("The Employee Details here :");
		System.out.println("Employee name : "+obj.getName());
		System.out.println("Employee Age : "+obj.getAge());
		System.out.println("Employee ID : "+obj.getEmployeeId());
		System.out.println("Employee annual salary : "+obj.annualSalary);
		obj.retire();
	}
}
