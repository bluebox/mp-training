package models;

public abstract class Person {

	private String name;
	private int age;
	private String contactDetails;

	public Person(String name, int age, String contactDetails) {
		this.name = name;
		this.age = age;
		this.contactDetails = contactDetails;
	}

	public Person(String name, int age) {
		this(name, age, null);
	}

	public Person() {

	}

	public String getName() {
		return name;
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

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public abstract String showDetails();

}
