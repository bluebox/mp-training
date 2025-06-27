
public class ValidAge {
	
	private String firstName;
	private String lastName;
	int age;
	
	

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
	
	public boolean isTeen() {
		if(age>12 && age<20) {
			return true;
		}
		return false;
	}
	
	public String getFullName() {
		if(firstName.isEmpty() && lastName.isEmpty()) {
			return "";
		}else if(firstName.isEmpty()) {
			return lastName;
		}else if(lastName.isEmpty()) {
			return firstName;
		}
		return firstName+" "+lastName;
			
	}



	public static void main(String[] args) {
			ValidAge person=new ValidAge();
			
			person.setFirstName("");
			person.setLastName("");
			person.setAge(10);
			
			System.out.println("fullName = "+person.getFullName());
			System.out.println("teen = "+person.isTeen());
			person.setFirstName("John");
			person.setAge(18);
			
			System.out.println("fullName = "+person.getFullName());
			System.out.println("teen = "+person.isTeen());
			person.setLastName("Smith");
			System.out.println("fullName = "+person.getFullName());


}

}
