package models;

public class Member extends Person {
	private int id;

	public Member(String name, int age, String contactDetails) {
		super(name, age);
		this.setContactDetails(contactDetails);
	}

	public Member(String name, int age) {
		this(name, age, null);
	}

	public Member() {

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

	@Override
	public String showDetails() {
		return "Member ID: " + id + " Name     : " + getName() + " Age      : " + getAge() + " Contact  : "
				+ getContactDetails();
	}
}
