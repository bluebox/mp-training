package inheritance;

public class HourlyEmployee extends Empolyee {
	private double hourlyPayRate;
	

	public HourlyEmployee(String name, String birthYear, String endDate, long employeeId, String hireDate, double hourlyPayRate ) {
		super(name, birthYear, endDate, employeeId, hireDate);
		// TODO Auto-generated constructor stub
		this.hourlyPayRate=hourlyPayRate;
	}
	@Override
	public double collectPay() {
		return hourlyPayRate*30;
	}
	public void  getDoublePay() {
		System.out.println("Double Pay recived");
	}

}