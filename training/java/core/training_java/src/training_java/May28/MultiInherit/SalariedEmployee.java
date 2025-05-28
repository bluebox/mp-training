package MultiInherit;

public class SalariedEmployee extends Employee {
	double annualSalary;
	boolean isRetired;
	SalariedEmployee(String name,String birthDate,String endDate,long empId,String hireDate, double annualSalry,boolean isRetired){
		super(name,birthDate,endDate,empId,hireDate);
		this.annualSalary=annualSalary;
		this.isRetired=isRetired;
	}
	public void retire() {
		if(isRetired) {
			System.out.println("Employee is Retired");
		}
		else {
			System.out.println("Employee is not retired");
		}
	}

}
