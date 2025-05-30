package MobilePhoneContactsChallenge;
import java.util.List;
import java.util.ArrayList;

public class MobilePhone {
	private List<Contact> myContacts= new ArrayList<Contact>();
//	private ArrayList<Contact> myContacts;
	private String myNumber;
	
	MobilePhone(String myNumber)
	{
		this.myNumber = myNumber;
		myContacts = new ArrayList<Contact>();
	}
	
	public boolean addNewContact(Contact contact)
	{
		if(findContact(contact.getName()) >= 0)
		{
			return false;
		}
		myContacts.add(contact);
		return true;
	}
	
	public boolean updateContact(Contact oldContact, Contact newContact)
	{
		int oldContactFoundAt = findContact(oldContact);
		if(oldContactFoundAt < 0)
		{
			return false;
		}
		
		int existingContactFoundAt = findContact(newContact);
		
		// If new contact name already exists and at an index different from that of old contact
		if(existingContactFoundAt >= 0 && existingContactFoundAt != oldContactFoundAt)
		{
			return false;
		}
		myContacts.set(oldContactFoundAt, newContact);
		return true;
	}
	
	public boolean removeContact(Contact contact)
	{
		if(findContact(contact) >= 0)
		{
			myContacts.remove(findContact(contact));
			return true;
		}
		return false;
	}
	
	public int findContact(Contact contact)
	{
		int foundAt = myContacts.indexOf(contact);
		if(foundAt >= 0)
		{
			return foundAt;
		}
		return -1;
	}
	
	public int findContact(String contactName)
	{
		for(int i = 0; i<myContacts.size(); i++)
		{
			if(myContacts.get(i).getName().equals(contactName) == true)
			{
				return i;
			}
		}
		return -1;
	}
	
	public Contact queryContact(String contactName)
	{
		int foundAt = findContact(contactName);
		if(foundAt >= 0)
		{
			return myContacts.get(foundAt);
		}
		return null;
	}
	
	public void printContacts()
	{
		System.out.println("Contact List: ");
		int i = 1;
		for(Contact contact: myContacts)
		{
			System.out.println(i + ". " + contact.getName() + " -> " + contact.getPhoneNumber());
			i++;
		}
	}
}
