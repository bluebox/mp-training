package MultiInherit;

public class HourlyEmployee extends Employee{
	double hourlyPayRate;
	HourlyEmployee(String name,String birthDate,String endDate,long empId,String hireDate,double hourlyPayRate){
		super(name,birthDate,endDate,empId,hireDate);
		this.hourlyPayRate=hourlyPayRate;
	}
	public double getDoublePay() {
		return hourlyPayRate;
	}

}
