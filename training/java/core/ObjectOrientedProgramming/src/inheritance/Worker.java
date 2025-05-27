package inheritance;

public class Worker {
	
	private String name;
	private String birthDate;
	private String endDate;
	public Worker(String name, String birthDate, String endDate) {
		this.name = name;
		this.birthDate = birthDate;
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getAge()
	{
		int age = 0;
		String[] dates = birthDate.split("[./-]");
		try {
			age =2025- Integer.parseInt(dates[dates.length-1]);
		}catch(NumberFormatException nfe)
		{
			System.out.println("Problem in dob format\nRe-enter in DD/MM/YYYY");
		}
		return age;
	}
	
	public double collectPay()
	{
		return 0.0;
	}
	
	public void terminate(String endDate)
	{
		this.endDate = endDate;
		System.out.println("The employee is terminated");
	}
	
	
	@Override
	public String toString() {
		return "Worker [name=" + name + ", birthDate=" + birthDate + ", endDate=" + endDate + "]";
	}
	
}
