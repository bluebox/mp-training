
public class Person {
	private String firstName;
	private String secondName;
	private int age;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isTeen()	{
		return age>=13&&age<=19;
	}
	public String getFullName() {
		boolean firstEmpty=(firstName==null || firstName.isEmpty());
		boolean lastEmpty=(secondName==null || secondName.isEmpty());
		if(firstEmpty && lastEmpty) {
			return "";
		}else if(firstEmpty) {
			return secondName;
		}else if(lastEmpty) {
			return firstName;
		}else {
			return firstName+""+secondName;
		}
		
	}
	
}
