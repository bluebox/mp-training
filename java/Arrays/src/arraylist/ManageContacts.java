package arraylist;

import java.util.ArrayList;

public class ManageContacts {
	private String myNumber;
	private  ArrayList<contact> myContacts;
	public  ManageContacts(String myNumber) {
		this.myNumber= myNumber;
		this.myContacts = new ArrayList<contact>();
}
	private int findContact(contact Contact) {
		for(int i=0;i<myContacts.size();i++) {
			if(myContacts.get(i).getName()== Contact.getName())
				return i;
		}
		return -1;
	}
	private contact queryContact(String name) {
		for(int i=0;i<myContacts.size();i++) {
			if(myContacts.get(i).getName()== name)
				return myContacts.get(i);
		}
		return null;
	}
	public void addContact(contact Contact) {
		if(findContact(Contact) == -1) {
			myContacts.add(Contact);
			System.out.println(Contact.getName()+ " added");
		}
		else {
			System.out.println(Contact.getName()+" Already exists ");
		}
		}
	public void removeContact(contact Contact) {
		if(findContact(Contact) > -1) {
			myContacts.remove(myContacts.indexOf(Contact));
			System.out.println(Contact.getName()+" removed");
	}
		else {
			System.out.println(Contact.getName()+" Contact not found");
		}
	}
	public void printContacts() {
		for(int i=0; i< myContacts.size();i++) {
			contact mycontact = myContacts.get(i);
			System.out.println("name:"+mycontact.getName()+" -> "+mycontact.getPhoneNumber());
		}
	}
public static void main(String [] args) {
	ArrayList< contact > myContacts = new ArrayList<contact>();
	ManageContacts myContacts1= new ManageContacts("998951");
	contact c1 = new contact("ram","123456");
	contact c2 = new contact("ram","123456");
	contact c4 = new contact("laxmana","123456");
	contact c3 = new contact("ravana","123456");
	myContacts1.addContact(c1);
//	myContacts1.addContact(c1);
	myContacts1.addContact(c2);
	myContacts1.addContact(c4);
	myContacts1.addContact(c3);
	contact c5 = new contact("hanuma","234567");
//	printContacts(myContacts1);
//	System.out.println(myContacts1);
//	for(int i=0; i< myContacts.size();i++) {
//		contact mycontact = myContacts.get(i);
//		System.out.println("name:"+mycontact.getName()+" phone:"+mycontact.getPhoneNumber());
//	}
	myContacts1.printContacts();
	myContacts1.removeContact(c5);
	myContacts1.addContact(c5);
	myContacts1.removeContact(c3);
	myContacts1.printContacts();
	contact query = myContacts1.queryContact("laxman");
	if(query == null) {
		System.out.println("no contact named like that");
	}
	else {
	System.out.println("found "+query.getName());
	}
	
}
}
