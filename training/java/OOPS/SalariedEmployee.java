
public class SalariedEmployee extends Employee{
	private double annualSalary;
	private boolean isRetired;
	
	public void retire()
	{
		this.isRetired=true;
	}
	SalariedEmployee( String eName,double annualSalary,boolean isRetired,String hireDate,long empId,String birthDate)
	{	super(eName,birthDate,empId,hireDate);
		this.annualSalary= annualSalary;
		this.isRetired=isRetired;
	}
	public void employeeData()
	{
		super.employeeData();
		System.out.println(
				this.annualSalary+" ,"
				+this.isRetired
				
				);
	}
}
