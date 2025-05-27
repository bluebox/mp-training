
public class Worker extends Employee{
private String name;
private String birthDate;
private String endDate;

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
	public int getAge() {
		System.out.println("Getting age...");
		int age=Integer.parseInt((endDate).substring(6)) - Integer.parseInt((birthDate).substring(6));
		//System.out.println("Age is "+age);
		return age;
	}
	public double collectPay() {
		double salary=getAnnualSalary();
		return salary;
	}
	void terminate(String endDate) {
		setEndDate(endDate);
	}

}
