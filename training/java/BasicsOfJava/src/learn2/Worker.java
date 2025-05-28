package learn2;

public class Worker {
	private String name;
	private String birthDate;
	private String endDate;
	private int age;
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void terminate(String endDate) {
		
		System.out.println("the last date is "+ endDate);
	}
	public double collectPay() {
		
		return 0.0;
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
}
