package collections;

import java.util.ArrayList;
import java.util.List;

public class Contacts {

	public static void main(String[] args) {
		
		Contact contact1=new Contact("ramsai","7993285158");
		Contact contact2=new Contact("ram","7993285150");
		Contact contact3=new Contact("sai","7993285159");
		List <Contact> list=  new ArrayList <> (List.of(contact1,contact2,contact3));
		MobilePhone mph= new MobilePhone("9381479267",list);
		mph.printContacts();
		mph.createContact(new Contact("rakesh","567899876"));
		mph.printContacts();
		System.out.println(mph.findContact("ram"));
		System.out.println(mph.removeContact("ram"));
		
		Contact cont= mph.queryContact(contact1.getName());
		System.out.println(mph.updateContact(contact1,new Contact("ramsai","9440141537")));
		System.out.println(cont);
		mph.printContacts();
				
	}
}
class MobilePhone 
{
	private String myNumber;
	private List<Contact> myContacts;
	
	public MobilePhone(String myNumber, List<Contact> arrayList) {
		super();
		this.myNumber = myNumber;
		this.myContacts = new ArrayList<>(List.copyOf(arrayList));
	}
	
	public boolean updateContact(Contact oldContact,Contact newContact)
	{
		int idx = myContacts.indexOf(oldContact);
		myContacts.remove(oldContact);
		myContacts.set(idx,newContact);
		
//		cont=newContact;
//		System.out.println(cont);
		//int i=0;
//		for(Contact contact:myContacts)
//		{
//			if(contact.getName() == oldContact.getName())
//			{
//				this.removeContact(oldContact.getName());
//				 myContacts.add(i, newContact);
//				 return true;
//			}
//			i++;
//		}
		return true;
	}
	
	public void printContacts()
	{
		for(Contact contact:myContacts)
		{
			System.out.println("name:" +contact.getName()+" number:"+contact.getNumber());
		}
	}
	
	public boolean removeContact(String name)
	{
		Contact contact= queryContact(name);
		if(contact !=null)
			myContacts.remove(contact);
		return contact!=null;
	}
	
	public boolean createContact(Contact newContact)
	{
		return this.myContacts.add(newContact);
	}
	
	public boolean findContact(String name)
	{
		
		return queryContact(name) != null;
	}
	
	public Contact queryContact(String name)
	{
		for(Contact contact:myContacts)
		{
			if(contact.getName() == name)
			{
				return contact;
			}
		}
		return null;
	}
	

}
class Contact
{
	private String name;
	private String number;
	public Contact(String name, String number) {
		super();
		this.name = name;
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public String getNumber() {
		return number;
	}
	public static Contact createContact(String name,String number)
	{
		Contact newContact= new Contact(name,number);
		return newContact;
	}
	@Override
	public String toString() {
		return "Contact [name=" + name + ", number=" + number + "]";
	}
}
