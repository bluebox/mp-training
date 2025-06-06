package WorkerEmployeeChallenge;

public class Worker {
	private String name;
	private String birthDate;
	private String endDate;
	
	public Worker(String name, String birthDate)
	{
		this.name = name;
		this.birthDate = birthDate;
	}
	public int getAge()
	{
		int birthYear = Integer.parseInt(birthDate);
		int currentYear = 2025;
		return currentYear - birthYear;
	}
	public String getName()
	{
		return name;
	}
	public double collectPay()
	{
		return 0.0;
	}
	public void terminate(String endDate)
	{
		this.endDate = endDate;
		System.out.println(name + "has been terminated on " + endDate);
	}
}
