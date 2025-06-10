package mobilePhoneContacts;

public class Contact {
	private String name;
	private String phoneNumber;
	public Contact(String name, String phoneNumber){
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	String getName() {
		return this.name;
	}
	String getPhoneNumber() {
		return this.phoneNumber;
	}
	static Contact createContact(String name,String phoneNumber) {
		return new Contact(name,phoneNumber);
	}
}
