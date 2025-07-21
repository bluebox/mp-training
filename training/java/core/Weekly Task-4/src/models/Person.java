package models;

public abstract class Person {
	private String name;
	private int age;
	private String gender;
	
	public Person(String name,String gender,int age) {
		this.name=name;
		this.gender=gender;
		this.age=age;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public abstract void showDetails();
}
