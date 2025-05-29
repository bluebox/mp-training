package arraypractice;

import java.util.*;

public class PhoneContact {

	public static void main(String[] args) {
	    Contact abcd=Contact.createContact("abcd", "9999999999");
	    MobilePhone aaaa=new MobilePhone("aaaa");
	    if(aaaa.addNewContact(abcd)) {
	    	System.out.println("Added new contact successfully.");
	    }
	    else System.out.println("Contact already exists.");
	    if(aaaa.addNewContact(abcd)) {
	    	System.out.println("Added new contact successfully.");
	    }
	    else System.out.println("Contact already exists.");
	    aaaa.printContact();
	    aaaa.removeContact(abcd);
	    aaaa.printContact();
	    aaaa.addNewContact(abcd);
	    Contact qwerty=Contact.createContact("qwerty", "8888888888");
	    aaaa.updateContact(abcd, qwerty);
	    aaaa.printContact();
	}

}

class MobilePhone{
	public String myNumber;
	public ArrayList<Contact> ans;
	public MobilePhone(String number) {
		this.myNumber=number;
		this.ans=new ArrayList<>();
	}
	public boolean addNewContact(Contact contact) {
		System.out.println("\nAdding contact...");
		 if(this.ans.contains(contact))return false;
		 this.ans.add(contact);
		 return true;
	}
	public boolean updateContact(Contact oldcontact,Contact newcontact) {
		System.out.println("Updating contact details...");
		if(this.ans.contains(oldcontact)) {
		    this.ans.remove(oldcontact);
		    this.ans.add(newcontact);
		    return true;
		}
		return false;
	}
	public boolean removeContact(Contact contact) {
		if(this.ans.contains(contact)) {
			this.ans.remove(contact);
			return true;
		}
		return false;
	}
	
	public int findContact(Contact contact) {
		return this.ans.indexOf(contact);
	}
	
	public int findContact(String names) {
		for(int i=0;i<this.ans.size();i++) {
			Contact ele=this.ans.get(i);
			if(ele.name==names)return i;
		}
		return -1;
	}
	
	public Contact queryContact(String name) {
		for(Contact ele:this.ans) {
			if(ele.name==name)return ele;
		}
		return null;
	}
	
	public void printContact() {
		System.out.println("\nPrinting Contacts : ");
		if(this.ans.size()==0) {
			System.out.println("Nothing to print.");
			return ;
		}
		for(Contact ele:this.ans) {
			System.out.println(ele.name+"'s phone number is "+ele.phoneNumber);
		}
	}
}
class Contact{
	public String name;
	public String phoneNumber;
	public Contact(String name,String phoneNumber) {
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	public String getName() {
		return this.name;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public static Contact createContact(String name,String phoneNumber) {
		Contact ans=new Contact(name,phoneNumber);
		return ans;
	}
}
