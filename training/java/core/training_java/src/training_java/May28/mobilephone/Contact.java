package mobilephone;

public class Contact {
	private String name;
	private String phoneNumber;
public Contact(String name,String phoneNumber){
	this.name=name;
	this.phoneNumber=phoneNumber;
}
public String getName() {
	return name;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public static Contact createContact(String name,String phoneNumber) {
	Contact con=new Contact(name,phoneNumber);
	return con;
}
public static void main(String[] args) {
MobilePhone mp=new MobilePhone("9823461");
MobilePhone.myContacts.add(createContact("Bob","87321021"));
MobilePhone.myContacts.add(createContact("Alex","214618"));
MobilePhone.myContacts.add(createContact("Charlie","652316"));
System.out.println(mp.addNewContact(createContact("David","9816901")));
System.out.println(mp.removeContact(createContact("Bob","87321021")));
System.out.println(MobilePhone.myContacts);

}
}
