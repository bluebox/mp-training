package inheritance;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SalariedEmployee extends Employee {
	private double annulaSalary;
	private boolean isRetired;
	public SalariedEmployee(String name, String birthDate, String endDate, long employeeId, String hireDate,
			double annulaSalary, boolean isRetired) {
		super(name, birthDate, endDate, employeeId, hireDate);
		this.annulaSalary = annulaSalary;
		this.isRetired = isRetired;
	}
	public double getAnnulaSalary() {
		return annulaSalary;
	}
	public void setAnnulaSalary(double annulaSalary) {
		this.annulaSalary = annulaSalary;
	}
	public boolean isRetired() {
		return isRetired;
	}
	public void setRetired(boolean isRetired) {
		this.isRetired = isRetired;
	}
	
	@Override
	public double collectPay()
	{
		return annulaSalary/12;
	}
	
	public void retire()
	{
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("DD-MM-yyyy");
		String terminationDate = currentDate.format(date);
		super.terminate(terminationDate);
		String day = terminationDate.split("-")[0];
		double pay = collectPay()*(Integer.parseInt(day)/28);
		isRetired = true;
		System.out.println(getEmployeeId()+" Employee retired.\nPay = "+pay);
	}
	@Override
	public String toString() {
		return super.toString()+"SalariedEmployee [annulaSalary=" + annulaSalary + ", isRetired=" + isRetired + ", employeeId="
				+ employeeId + ", hireDate=" + hireDate + "]";
	}
	
	
	

}
