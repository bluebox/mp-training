package adv.medplus;

import java.util.ArrayList;
import java.util.List;

public class PhoneContacts {
	
	
	public static void main(String[] args) {
		
		MobilePhone cell = new MobilePhone("123546");
		Contact contact = Contact.createContact("saketh","5514894984");
		Contact contact1 = Contact.createContact("surya","5146545");
		Contact contact2 = Contact.createContact("bhushan","87986516");
		Contact contact3 = Contact.createContact("shfiuhbaewf", "5489789");
		
		System.out.println("Adding new contact : "+	cell.addNewContact(contact));
		System.out.println("Adding new contact : "+	cell.addNewContact(contact1));
		System.out.println("Adding new contact : "+	cell.addNewContact(contact2));
		System.out.println("updating contact : "+cell.updateContact(contact1, contact3));
		System.out.println("removing contact : "+cell.removeContact(Contact.createContact("vbaibd","9084534")));
		System.out.println("Finding Contact : "+cell.findContact(contact2));
		System.out.println("finding contact throung name : "+cell.findContact(contact2.getName()));
		System.out.println("quering contact : "+cell.queryContact("bhushan"));
		cell.printContacts();
		

		
	}

}

class Contact{
	private String name;
	private String phoneNumber;
	
	public Contact(String name,String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public static Contact createContact(String name,String phoneNumber) {
		return new Contact(name,phoneNumber);
	}
	
}

class MobilePhone {
	
	private String myNumber;
	
	private List<Contact> myContacts ;
	
	public MobilePhone(String number) {
		myNumber = number;
		myContacts = new ArrayList<>();
		
	}
	
	public boolean addNewContact(Contact number) {
		for (Contact i : myContacts) {
			if (number==i) return false;
		}
		myContacts.add(number);
		return true;
	}
	
	public boolean updateContact(Contact old,Contact New) {
		for (Contact i : myContacts) {
			if (old==i) {
				myContacts.set(myContacts.indexOf(i),New);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeContact(Contact contact) {
		for (Contact i : myContacts) {
			if (contact==i) {
				myContacts.remove(myContacts.indexOf(i));
				return true;
			}
		}
		return false;
	}
	
	public int findContact(Contact contact) {
		for (Contact i : myContacts) {
			if (contact==i) {
				return myContacts.indexOf(i);
			}
		}
		return -1;
	}
	public int findContact(String contact) {
		for (Contact i : myContacts) {
			if (contact==i.getName()) {
				return myContacts.indexOf(i);
			}
		}
		return -1;
	}
	public String queryContact(String contact) {
		for (Contact i : myContacts) {
			if (contact==i.getName()) {
				return i.getPhoneNumber();
			}
		}
		return "Contact not Found";
	}
	
	public void printContacts() {
		System.out.println("The contacts in the mobile phone : ");
		for (Contact i : myContacts) {
			System.out.println(i.getName()+ " -> "+i.getPhoneNumber());
		}
	}
	
	
}










