package MultiInherit;

public class Worker {
	private String name;
	private String birthDate;
	private String endDate;
	public Worker(String name,String birthDate,String endDate) {
		this.name=name;
		this.birthDate=birthDate;
		this.endDate=endDate;
	}
	public int getAge() {
		return (Integer.parseInt(endDate.substring(6))-Integer.parseInt(birthDate.substring(6)));
	}
	public double collectPay() {
		return 1000;
	}
	public void terminate(String endDate) {
		System.out.println("Your employment terminates at "+ endDate);
	}

}
