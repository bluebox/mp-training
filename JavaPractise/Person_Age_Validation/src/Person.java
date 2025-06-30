
public class Person{
	private String firstName;
	private String lastName;
	private int age;
	//public Person(String fname, String lname,int age) {
	//	firstName=fname;
		//lastName=lname;
	//	this.age = age;
		
	//}
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
		if(age<0 || age>100) {
			this.age=0;
		}
		else {
		  this.age = age;
		}
	}
	public boolean isTeen() {
		if(age>12 && age<20) {
			return true;
		}
		return false;
	}
	public String getFulName() {
		if(firstName==null && lastName==null) {
			return "";
		}
		if(firstName==null) return lastName;
		if(lastName==null) return firstName;
		return firstName+lastName;
	}

}
