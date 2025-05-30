package MobilePhoneContactsChallenge;

public class Main {
	public static void main(String [] args)
	{
		MobilePhone myMobileContact = new MobilePhone("9123482914");
		
		Contact contact1 = Contact.createContact("Bruce", "16180339");
		Contact contact2 = Contact.createContact("Constantine", "66180339");
		Contact contact3 = Contact.createContact("Barry", "19180339");
		Contact contact4 = Contact.createContact("Clark", "46180339");
		
		myMobileContact.addNewContact(contact1);
		myMobileContact.addNewContact(contact2);
		myMobileContact.addNewContact(contact3);
		myMobileContact.addNewContact(contact4);
		
		Contact updatedContact = Contact.createContact("Diana", "29180339");
		boolean updateStatus = myMobileContact.updateContact(contact3, updatedContact);
		
		if(updateStatus == true)
		{
			System.out.println("Contact " + contact3.getName() + " | " + contact3.getPhoneNumber() + " has been successfully updated to : " + updatedContact.getName() + " | " + updatedContact.getPhoneNumber());
		}
		else
		{
			System.out.println("Updation unsuccessfull");
		}
		
		boolean removalStatus = myMobileContact.removeContact(contact3);
		
		if(removalStatus == true)
		{
			System.out.println("Contact " + contact3.getName() + " | " + contact3.getPhoneNumber() + " has been successfully removed.");
		}
		else
		{
			System.out.println("Contact cannot be removed.");
		}
		
		Contact queried = myMobileContact.queryContact("Diana");
		
		if(queried != null)
		{
			System.out.println("Your requested contact has been retrieved : " + queried.getName() + " | " + queried.getPhoneNumber());
		}
		else
		{
			System.out.println("Your requrested contact does not exist.");
		}
		
		myMobileContact.printContacts();
	}
}
