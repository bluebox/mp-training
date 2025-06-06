package day10.mobilephone;

public class Contacts {
private String name,phoneNumber;
Contacts(String name,String phoneNumber){
	this.name=name;
	this.phoneNumber=phoneNumber;
}
public String getName() {
	return this.name;
}
public String getPhoneNumber() {
	return this.phoneNumber;
}

public static Contacts  createContact(String name,String phoneNumber) {
	return new Contacts(name,phoneNumber);
}
}
