package samplecodes;

import java.util.*;

public class MobilePhoneContacts {

	public static void main(String[] args) {
	    
	    MobilePhone phone = new MobilePhone("1234567890");
	    
	  
	    Contact contact1 = Contact.createContact("Sameer", "555-0123");
	    Contact contact2 = Contact.createContact("Sai", "555-0456");
	    Contact contact3 = Contact.createContact("Bharath", "555-0789");
	    
	   
	    System.out.println("Adding contacts...");
	    System.out.println("Added sameer: " + phone.addNewContact(contact1));
	    System.out.println("Added sai: " + phone.addNewContact(contact2));
	    System.out.println("Added Bharath: " + phone.addNewContact(contact3));
	    System.out.println("Adding duplicate Sameer: " + phone.addNewContact(contact1));
	    
	    
	    System.out.println("\nContact List:");
	    phone.printContacts();
	    
	    
	    System.out.println("\nFinding contacts...");
	    System.out.println("Sameer's index: " + phone.findContact(contact1));
	    System.out.println("Sai's index (by name): " + phone.findContact("Sai"));
	    System.out.println("Non-existent contact: " + phone.findContact("Arun"));
	    
	   
	    System.out.println("\nQuerying contact...");
	    Contact queried = phone.queryContact("Sameer");
	    if (queried != null) {
	        System.out.println("Found: " + queried.getName() + " -> " + queried.getPhoneNumber());
	    }
	 
	    System.out.println("\nUpdating contact...");
	    Contact newContact = Contact.createContact("Jayanth", "555-9999");
	    System.out.println("Updated Jayanth: " + phone.updateContact(contact1, newContact));
	    

	    System.out.println("\nUpdated Contact List:");
	    phone.printContacts();
	    

	    System.out.println("\nRemoving contact...");
	    System.out.println("Removed sai: " + phone.removeContact(contact2));
	    
	    
	    System.out.println("\nFinal Contact List:");
	    phone.printContacts();
	}

}
class MobilePhone{
	private String myNumber;
	ArrayList<Contact> myContacts;
	MobilePhone(String myNumber){
		this.myNumber=myNumber;
		this.myContacts=new ArrayList<>();
	}
	public boolean addNewContact(Contact c) {
		if(!myContacts.contains(c)) {
			myContacts.add(c);
			return true;
		}
		return false;
	}
	public boolean updateContact(Contact old,Contact newContact) {
		if(!myContacts.contains(old)) return false;
		old.setName(newContact.getName());
		old.setPhoneNumber(newContact.getPhoneNumber());
		return true;
	}
	public boolean removeContact(Contact toRemove) {
		if(myContacts.contains(toRemove)) {
			myContacts.remove(toRemove);
			return true;
		}
		return false;
	}
	public int findContact(Contact toFind) {
		if(myContacts.contains(toFind)) {
			return myContacts.indexOf(toFind);
		}
		return -1;
	}
	public int findContact(String s) {
		for(Contact c:myContacts) {
			if(s.equals(c.getName())) return 0;
		}
		return -1;
	}
	public Contact queryContact(String name) {
		for(Contact c:myContacts) {
			if(name.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}
	public void printContacts() {
		for(Contact c:myContacts) {
			System.out.println(c.getName()+" -> "+c.getPhoneNumber());
		}
	}
}
class Contact{
	private String name;
	private String phoneNumber;
	public Contact(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public static Contact createContact(String a,String b) {
		Contact c=new Contact(a,b);
		return c;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setPhoneNumber(String phone) {
		this.phoneNumber=phone;
	}
}
