package MobileProject;

public class Contacts {
	String name;
	String phonenumber;
	public Contacts(String name,String phoneNumber) {
		this.name=name;
		this.phonenumber=phoneNumber;
	}
	public String getName() {
		System.out.println("Name: "+this.name);
		return this.name;
	}
	public void getPhoneNumber() {
		System.out.println("Number: "+this.phonenumber);

	}
	
	public static Contacts createContact(String name,String phoneNumber) {
		return new Contacts(name,phoneNumber);
	}
}
