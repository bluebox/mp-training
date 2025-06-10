package mobilePhoneContacts;

import java.util.*;

public class MobilePhone {
	private ArrayList<Contact> myContacts;
	public MobilePhone(){
		myContacts = new ArrayList<>();
	}
	boolean addNewContact(Contact c) {
		Iterator<Contact> x= this.myContacts.iterator();
		while(x.hasNext()) {
			Contact y= x.next();
			if(y.getName().equals(c.getName())) {
				return false;
			}
		}
		myContacts.add(c);
		return true;
	}
	boolean updateContact(Contact o, Contact n) {
		Iterator<Contact> iterator= this.myContacts.iterator();
		while(iterator.hasNext()) {
			Contact x= iterator.next();
			if(x.getName().equals(n.getName())) {
				return false;
			}
		}
		int x= this.myContacts.indexOf(o);
		this.myContacts.set(x,n);
		return true;
	}
	boolean removeContact(Contact c) {
		Iterator<Contact> iterator= this.myContacts.iterator();
		while(iterator.hasNext()) {
			Contact x= iterator.next();
			if(x.getName().equals(c.getName())) {
				this.myContacts.remove(x);
//				printContacts();
				return true;
			}
		}
		return false;
		
	}
	int findContact(Contact c) {
		Iterator<Contact> iterator= this.myContacts.iterator();
		while(iterator.hasNext()) {
			Contact x= iterator.next();
			if(x.getName().equals(c.getName())) {
				this.myContacts.remove(c);
				return myContacts.indexOf(x);
			}
		}
		return -1;
	}
	int findContact(String s) {
		Iterator<Contact> iterator= this.myContacts.iterator();
		while(iterator.hasNext()) {
			Contact x= iterator.next();
			if(x.getName().equals(s)) {
				return myContacts.indexOf(x);
			}
		}
		return -1;
	}
	Contact queryContact(String s) {
		Iterator<Contact> iterator= this.myContacts.iterator();
		while(iterator.hasNext()) {
			Contact x= iterator.next();
			if(x.getName().equals(s)) {
				return x;
			}
//			iterator.next();
		}
		return null;
	}
	void printContacts() {
		System.out.println("Contact List:");
		Iterator<Contact> iterator= this.myContacts.iterator();
		while(iterator.hasNext()) {
			Contact x= iterator.next();
			System.out.println(x.getName()+" -> "+x.getPhoneNumber());
		}
	}
}
