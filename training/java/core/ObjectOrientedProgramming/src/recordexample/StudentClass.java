package recordexample;

public class StudentClass {
	
	private String name;
	private String rollNumber;
	private String branch;
	private int age;
	private int year;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public StudentClass(String name, String rollNumber, String branch, int age, int year) {
		this.name = name;
		this.rollNumber = rollNumber;
		this.branch = branch;
		this.age = age;
		this.year = year;
	}
	@Override
	public String toString() {
		return "StudentClass [name=" + name + ", rollNumber=" + rollNumber + ", branch=" + branch + ", age=" + age
				+ ", year=" + year + "]";
	}
	
	

}
