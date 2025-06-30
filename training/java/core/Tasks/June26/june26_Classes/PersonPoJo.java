package corejava.june26_Classes;

public class PersonPoJo {
	private String firstName;
	private String lastName;
	private int age;
	
	public PersonPoJo(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String fullName() {
		if(!this.firstName.isEmpty() && !this.lastName.isEmpty()) {
			return this.firstName+this.lastName;
		}
		else if(this.firstName.isEmpty()) {
			return this.lastName;
		}
		else if(this.lastName.isEmpty()) {
			return this.firstName;
		}
		else {
			return "";
		}
	}
	public boolean isTeen() {
		if(this.age>12 && this.age<20)
			return true;
		return false;
	}
}
