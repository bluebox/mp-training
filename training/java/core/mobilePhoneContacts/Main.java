package mobilePhoneContacts;

public class Main {
	public static void main(String[] args) {
		MobilePhone mp=new MobilePhone();
		Contact c1=new Contact("abcd","1234567890");
		System.out.println(mp.addNewContact(c1));
		Contact c2=new Contact("xyz","999999999");
		System.out.println(mp.addNewContact(c2));
		Contact c3=new Contact("alice", "9876543210");
		System.out.println(mp.addNewContact(c3));
		Contact c4 = new Contact("alice", "9876543210");
		System.out.println(mp.findContact(c4));
		System.out.println(mp.removeContact(c4));
		System.out.println(mp.findContact("xyz"));
		System.out.println(mp.queryContact("abcd"));
		mp.printContacts();
		System.out.println(mp.updateContact(c2, c4));
		mp.printContacts();
	}
}
