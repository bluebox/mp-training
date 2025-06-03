package worker;

public class Worker {
	private String name,birthDate;
	protected String terminateDate;
	
	public  Worker(String name,String birthDate) {
		this.name=name;
		this.birthDate=birthDate;
	}
	
	public int getAge() {
		int age=(2025-(Integer.parseInt(birthDate)));
		return age;
//		System.out.println("Age of the Employee is : "+age);
	}
	
	public String getName() {
		return name;
	}
	
	public void terminate(String terminateDate) {
		this.terminateDate=terminateDate;
		System.out.println("The Employee terminte on : "+terminateDate);
	}
	
}
