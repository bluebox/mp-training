package MobileProject;

public class Main {
	public static void main(String[] args) {

		MobilePhone phone = new MobilePhone("9573991029");
		Contacts contact_1 = Contacts.createContact("Arjun", "8675646576");
		Contacts contact_2 = Contacts.createContact("Bhargava", "789758768");
		Contacts contact_3 = Contacts.createContact("chanakya", "800046576");
		Contacts contact_4 = Contacts.createContact("dhrona", "8675646576");

		phone.addNewContact(contact_1);
		phone.addNewContact(contact_2);
		phone.addNewContact(contact_3);
		phone.addNewContact(contact_4);
		phone.printContacts();
		
		System.out.println();
		System.out.println();

		MobilePhone phone_2 = new MobilePhone("123456778");
		Contacts contact_11 = Contacts.createContact("Arun", "8675646576");
		Contacts contact_22 = Contacts.createContact("Rhagava", "789758768");
		Contacts contact_33 = Contacts.createContact("charan", "800046576");
		Contacts contact_44 = Contacts.createContact("Raina", "8675646576");
		phone_2.addNewContact(contact_11);
		phone_2.addNewContact(contact_22);
		phone_2.addNewContact(contact_33);
		phone_2.addNewContact(contact_44);
		phone_2.printContacts();
		
		//updarte contact
		phone_2.updateContact(contact_11, contact_4);
		
		System.out.println();
		System.out.println();
//		phone_2.printContacts();
		//remove contact
		phone.removeContact(contact_3);
		phone.printContacts();

		//find
		int indexOfNumber=phone.findContact(contact_1);
		System.out.println("\n\nConatct Found at position: "+indexOfNumber);
		
		//query contact
		phone.queryContact("Bhargava");
	}
}
