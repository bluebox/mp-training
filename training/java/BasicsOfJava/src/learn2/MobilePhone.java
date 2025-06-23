package learn2;

import java.util.ArrayList;

public class MobilePhone {
	
	private String myNumber;
	private ArrayList<Contact> myContacts;
	
	public String getMyNumber() {
		return myNumber;
	}
	
	public MobilePhone(String myNumber,ArrayList<Contact> myContacts) {
		
		this.myNumber = myNumber;
		this.myContacts = myContacts;
	}
	
	public boolean addNewContact(Contact contact) 
	{
		int index = findContact(contact);
		if(index == -1) {
			myContacts.add(contact);
			return true;
		}
		System.out.println("Contact already exists ");
		return false;
	}
	
	public boolean updateContact(Contact oldContact,Contact newContact) {
		int index = findContact(oldContact);
		if(index != -1) {
			myContacts.remove(index);
			if(addNewContact(newContact)) {
				System.out.println("updated successfully ");
				return true;
			}
			else {
				System.out.println("New Contact already exists ");
			}
		}
		System.out.println("Old contact not found ");
		return false;
	}
	public boolean removeContact(Contact contact) {
		int index = findContact(contact);
		if(index == -1) {
			System.out.println("Contact to be removed not found with "+contact.getName());
			return false;			
		}
		else {
			myContacts.remove(index);
			System.out.println(" contact was successfully removed ");
			return true;
		}
	}
	
	private int findContact(Contact contactToFind) {
		
		for(int i = 0;i < myContacts.size(); i++) {
			if(myContacts.get(i).getName().equals(contactToFind.getName())) {

				System.out.println("Contact found at index "+i);
				return i;
			}
		}
		return -1;
	}
	
	private int findContact(String name) {
		for(int i = 0;i < myContacts.size(); i++) {
			if(myContacts.get(i).getName().equals(name)) {
				System.out.println("Contact found at index "+i);
				return i;
			}
		}
		return -1;
	}
	
	public Contact queryContact(String name) {
		int index = findContact(name);
		if(index == -1){
			return null;
		}
		System.out.println("name is "+myContacts.get(index).getName());
		System.out.println("Contact is "+myContacts.get(index).getPhoneNumber());
		return myContacts.get(index);
	}
	
	public void printContacts() {
		
		System.out.println("------------------------------");
		for(Contact contact : myContacts) {
			System.out.println(contact.getName() + " -> "+contact.getPhoneNumber());
		}
		System.out.println("_________________________________");
	}
}
