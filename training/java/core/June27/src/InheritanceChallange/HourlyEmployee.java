package InheritanceChallange;
public class HourlyEmployee extends Employee{
	private double hourlyPayRate;

	public HourlyEmployee(String name, String birthDate, String endDate, long employeeId, String hireDate,
			double hourlyPayRate) {
		super(name, birthDate, endDate, employeeId, hireDate);
		this.hourlyPayRate = hourlyPayRate;
	}

	public double getHourlyPayRate() {
		return hourlyPayRate;
	}

	public void setHourlyPayRate(double hourlyPayRate) {
		this.hourlyPayRate = hourlyPayRate;
	}
	
	public double getDoublePay()
	{
		hourlyPayRate *=2;
		return hourlyPayRate;
	}

	@Override
	public String toString() {
		return "HourlyEmployee [hourlyPayRate=" + hourlyPayRate + ", getHourlyPayRate()=" + getHourlyPayRate()
				+ ", getDoublePay()=" + getDoublePay() + ", getEmployeeId()=" + getEmployeeId() + ", getHireDate()="
				+ getHireDate() + ", getAge()=" + getAge() + ", terminate()=" + terminate() + ", getName()=" + getName()
				+ ", getBirthDate()=" + getBirthDate() + ", getEndDate()=" + getEndDate() + "]";
	}
	
	
}
