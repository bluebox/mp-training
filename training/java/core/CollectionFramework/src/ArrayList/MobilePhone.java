package ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class MobilePhone {
	private String myNumber;
	private ArrayList<Contacts> myContacts;
	public MobilePhone(String myNumber, ArrayList<Contacts> myContacts) {
		super();
		this.myNumber = myNumber;
		this.myContacts = myContacts;
	}
	
	public boolean addNewContact(Contacts contact)
	{
		boolean isPresent = myContacts.contains(contact);
		if(!isPresent)
		{
			myContacts.add(contact);
		}
		return !isPresent;
	}
	
	
	public boolean updateContact(Contacts oldContact,Contacts newContact)
	{
		boolean isPresent = myContacts.contains(oldContact);
		if(isPresent)
		{
			myContacts.set(myContacts.indexOf(oldContact),newContact);
		}
		return isPresent;
	}
	
	public boolean removeContact(Contacts contact)
	{
		
		return myContacts.remove(contact);
	}
	
	public int findContact(Contacts contact)
	{
		//return myContacts.indexOf(contact);
		int i = 0;
		for(var myContact:myContacts)
		{
			if(myContact.getName().equals(contact.getName()) && myContact.getPhoneNumber().equals(contact.getPhoneNumber()))
			{
				return i+1;
			}
		}
		return -1;
	}
	
	public int findContact(String contactName)
	{
		for(int i = 0;i<myContacts.size();i++)
		{
			if(myContacts.get(i).getName().equals(contactName))
			{
				return i+1;
			}
		}
		return -1;
	}
	public Contacts queryContact(String contactName)
	{
		for(var myContact:myContacts)
		{
			if(myContact.getName().equals(contactName))
			{
				return myContact;
			}
		}
		return null;
	}
	public void printContacts()
	{
		System.out.println("Contact List :: ");
		int i = 1;
		for(var contact:myContacts)
		{
			System.out.println(""+i+". "+contact.getName()+" -> "+contact.getPhoneNumber());
			i++;
		}
		
	}
}
