package models;

public class Member extends Person {
	private int id;

	public Member(int id, String name, int age, String contactDetails) {
		super(name, age, contactDetails);
		this.id = id;
	}

	public Member(String name, int age, String contactDetails) {
		super(name, age, contactDetails);
	}

	@Override
	public String showDetails() {

		return "Member ID: " + id + " Name : " + getName() + " age : " + getAge() + " contact : " + getContactDetails();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return showDetails();
	}

}
