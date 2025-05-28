
public class Worker {

	private String eName;
	private String birthDate;
	private String endDate;
	Worker()
	{
		
	}
	Worker(String eName,String birthDate)
	{
		this.eName=eName;
		this.birthDate=birthDate;
	}
	public String getName()
	{
		return this.eName;
	}
	  public double collectPay() {
		return 0;
	} 
	public void terminate(String endDate)
	{
		this.endDate=endDate;
	}
	public void employeeData()
	{
		System.out.println(
				
				this.eName+" ,"
				+ this.birthDate+" '"
				);
	}
	
}
