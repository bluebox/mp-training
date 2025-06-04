package challenges;

import java.io.Serializable;

public class EmployeeDetails implements Serializable{
	
	private String id;
	private String name;
	private int age;
	private String designation;
	private Contact contact;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(String email,String phoneNumber) {
		Contact contact = new Contact(phoneNumber, email);
		this.contact = contact;
	}
	public EmployeeDetails(String id, String name, int age, String designation, String email,String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.designation = designation;
		this.contact = new Contact(phoneNumber,email);
	}
	
	
	
	
	
	
}
