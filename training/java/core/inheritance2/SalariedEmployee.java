
public class SalariedEmployee{
	private double annualSalary;
	private boolean isRetired;
	
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

	void retire() {
		setRetired(true);
	}

}
