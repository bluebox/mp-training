package MobileProject;

import java.util.ArrayList;

public class MobilePhone {

	String myNumber;
	ArrayList<Contacts> myContacts;

	public MobilePhone(String phoneNumber) {
		this.myNumber = phoneNumber;
		this.myContacts = new ArrayList<>();
	}

	public boolean addNewContact(Contacts contact) {
		if (myContacts.contains(contact)) {
			System.out.println("Contact exists.");
			return false;
		}
		myContacts.add(contact);
//		for(int i=0;i<myContacts.size();i++) {
//			
//			Contacts contac=myContacts.get(i);
//			System.out.println(i+1);
//			contac.getName();
//			contac.getPhoneNumber();
//		}
		return true;
	}

	public boolean updateContact(Contacts oldContact, Contacts newContact) {
		if (myContacts.contains(oldContact)) {
			System.out.println("Contact will be updated.");
			myContacts.remove(oldContact);
			myContacts.add(newContact);
			return true;
		} else {
			System.out.println("Contact not found to update.");
			return false;
		}
	}

	public boolean removeContact(Contacts contact) {
		if (myContacts.contains(contact)) {
			myContacts.remove(contact);
			System.out.println("Contact removed successfully");
			return true;
		}
		System.out.println("Contact not found.Already deleted");
		return false;
	}

	public int findContact(Contacts contact) {
		if (myContacts.contains(contact)) {
			int index = myContacts.indexOf(contact);
			Contacts contac = myContacts.get(index);
			System.out.println("\n\nConatct Found at position: " + index + 1);
//			System.out.println(index + 1);
			contac.getName();
			contac.getPhoneNumber();
			return myContacts.indexOf(contact) + 1;
		}
		System.out.println("Contact not found.");
		return -1;

	}

	public int findContact(String name) {
		for (int i = 0; i < myContacts.size(); i++) {
			Contacts conta = myContacts.get(i);
			if (conta.getName().equals(name)) {
				System.out.println("Contact foung at index. " + i + 1);
				return i;
			}
		}
		System.out.println("Contact not found");
		return -1;

	}

	public Contacts queryContact(String name) {
		int index=findContact(name);
		Contacts contac=myContacts.get(index);
		if(index>=0) {
			System.out.println("\n\nConatct Found at position: "+index);
//			System.out.println(index + 1);
			contac.getName();
			contac.getPhoneNumber();
			return myContacts.get(index);
		}
		System.out.println("Contact not found");
		return null;
	}

	public void printContacts() {
		for (int i = 0; i < myContacts.size(); i++) {

			Contacts contac = myContacts.get(i);
			System.out.println(i + 1);
			contac.getName();
			contac.getPhoneNumber();
		}
	}

}
