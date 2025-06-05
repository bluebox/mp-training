
public class Contact {
	private String name;
	private String phoneNumber;
	
	public Contact(String name, String phonenumber) {
		this.name = name;
		this.phoneNumber = phonenumber;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public static Contact createContact(String name, String phoneNumber) {
		Contact newContact = new Contact(name, phoneNumber);
		return newContact;
	}
}
