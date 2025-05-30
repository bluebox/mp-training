package inheritance;

public class SalariedEmployee extends Empolyee{
	private double annualSalary;
	private boolean isRetired;
	public SalariedEmployee(String name, String birthYear, String endDate, long employeeId, String hireDate, double annualSalary) {
		super(name, birthYear, endDate, employeeId, hireDate);
		// TODO Auto-generated constructor stub
		this.annualSalary=annualSalary;
		this.isRetired=false;
		
	}
	public void retire() {
		System.out.println("Emplyee retiered and got bonus");
	}
	@Override
	public double collectPay() {
		return isRetired? (annualSalary*.9)/12: annualSalary/12;
		
	}
	
}
