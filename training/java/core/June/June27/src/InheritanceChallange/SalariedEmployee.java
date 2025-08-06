package InheritanceChallange;

public class SalariedEmployee extends Employee {
	private double annualSalary;
	private boolean isRetired;
	
	public SalariedEmployee(String name, String birthDate, String endDate, long employeeId, String hireDate,
			double annualSalary, boolean isRetired) {
		super(name, birthDate, endDate, employeeId, hireDate);
		this.annualSalary = annualSalary;
		this.isRetired = isRetired;
	}

	public double getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}

	public boolean isRetired() {
		return isRetired;
	}

	public void setRetired(boolean isRetired) {
		this.isRetired = isRetired;
	}
	
	public boolean retire() {
		isRetired = true;
		return isRetired;
	}
	
	public void collectPay() {
		System.out.println(getName() +" collected his salary of "+ annualSalary);
	}

	@Override
	public String toString() {
		return "SalariedEmployee [annualSalary=" + annualSalary + ", isRetired=" + isRetired + ", getEmployeeId()="
				+ getEmployeeId() + ", getHireDate()=" + getHireDate() + ", getAge()=" + getAge() + ", terminate()="
				+ terminate() + ", getName()=" + getName() + ", getBirthDate()=" + getBirthDate() + ", getEndDate()="
				+ getEndDate() + "]";
	}
	
	
}
