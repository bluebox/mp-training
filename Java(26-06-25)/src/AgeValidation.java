
public class AgeValidation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1=new Person();
		p1.setFirstName("");
		p1.setlastName("");
		p1.setAge(100);
		System.out.println("fullName= "+p1.getFullName());
		System.out.println("teen= "+p1.isTeen());
		p1.setFirstName("Jhon");
		p1.setAge(18);
		System.out.println("fullName= "+p1.getFullName());
		System.out.println("teen= "+p1.isTeen());
		p1.setlastName("Smith");
		System.out.println("fullName= "+p1.getFullName());		
	}
}
class Person
{
	private String firstName;
	private String lastName;
	private int age;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getlastName() {
		return lastName;
	}
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		if(age<0||age>100)
			return 0;
		else
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isTeen()
	{
		if(age>12&&age<30)
			return true;
		else
			return false;
	}
	public String getFullName() {
	    if (firstName.isEmpty() && lastName.isEmpty()) {
	        return "";
	    } else if (firstName.isEmpty()) {
	        return lastName;
	    } else if (lastName.isEmpty()) {
	        return firstName;
	    } else {
	        return firstName + " " + lastName;
	    }
	}

	}

