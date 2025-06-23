package learn2;

import java.util.ArrayList;

public class MainMobilePhone {
	
	public static void main(String[] args) {
		
		ArrayList<Contact> myContacts = new ArrayList<>();
		myContacts.add(Contact.createContact("Bob","321285"));
		myContacts.add(Contact.createContact("Alice","167989"));
		myContacts.add(Contact.createContact("Tom","113387"));
		myContacts.add(Contact.createContact("James","286432"));
		
		MobilePhone mobile = new MobilePhone("630482",myContacts);
		mobile.printContacts();
		mobile.addNewContact(new Contact("Sri","9848984"));
		mobile.printContacts();
		mobile.addNewContact(new Contact("rta","90900"));
		mobile.printContacts();
		mobile.removeContact(new Contact("rta","90900"));
		mobile.printContacts();
		mobile.addNewContact(new Contact("",""));
		mobile.updateContact(new Contact("",""), new Contact("Raj","984899"));
		mobile.printContacts();
		mobile.queryContact("Sri");
	}
}
