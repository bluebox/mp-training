import java.util.ArrayList;

public class MobilePhone {
	ArrayList<Contact> myContacts;
	private String myNumber;
	
	public MobilePhone(String myNumber) {
		this.myNumber = myNumber;
		this.myContacts = new ArrayList<>();
	}
	
	public static void main(String[] args) {
		MobilePhone myMobilePhone = new MobilePhone("7702402573");
		Contact suryaContact = new Contact("Surya", "8639317774");
		Contact rakeshContact = new Contact("Rakesh", "8962177521");
		Contact gayathriContact = new Contact("Gayathri", "9652829356");
		
		myMobilePhone.addNewContact(rakeshContact);
		myMobilePhone.addNewContact(suryaContact);
		myMobilePhone.addNewContact(new Contact("Surya", "9705672356"));
		myMobilePhone.addNewContact(gayathriContact);
		
		myMobilePhone.printContacts();
		System.out.println();
		
		myMobilePhone.updateContact(rakeshContact, new Contact("Rakesh T", "9100371441"));
		
		System.out.println("After updating rakesh, the total contacts are:\n");
		myMobilePhone.printContacts();
		System.out.println();
		
		myMobilePhone.removeContact(suryaContact);
		System.out.println("Surya's contact has been removed successfully!");
		myMobilePhone.printContacts();
		
		int idx = myMobilePhone.findContact(rakeshContact);
		System.out.println("The contact Rakesh is found at " + idx);
		int idxOfGayathri = myMobilePhone.findContact(gayathriContact);
		System.out.println("The contact Gayathri is found at " + idxOfGayathri);
//		System.out.println(gayathriContact);
//		System.out.println(myMobilePhone.queryContact("Gayathri"));
		
		System.out.println("My mobile number is: " + myMobilePhone.myNumber);
	}
	
	
	boolean addNewContact(Contact newContact) {
		int contactIdx = findContact(newContact);
		if(contactIdx != -1) return false;
		myContacts.add(newContact);
		return true;
	}
	
	
	boolean updateContact(Contact oldContact, Contact newContact) {
		int contactIdx = findContact(oldContact);
		if(contactIdx == -1) return false;
		
		myContacts.set(contactIdx, newContact);
		return true;
	}
	
	
	boolean removeContact(Contact contact) {
		int contactIdx = findContact(contact);
		if(contactIdx == -1) return false;
		
		myContacts.remove(contactIdx);
		return true;
	}
	
	
	int findContact(Contact contact) {
		return myContacts.indexOf(contact);
	}
	
	
	int findContact(String name) {
		for(int i=0;i<myContacts.size();i++) {
			if(myContacts.get(i).getName().equals(name)) return i;
		}
		return -1;
	}
	
	
	Contact queryContact(String name) {
		int idxOfContact = findContact(name);
		return myContacts.get(idxOfContact);
	}
	
	
	void printContacts() {
		System.out.println("Contact List:");
		for(Contact contact:myContacts) {
			System.out.println(contact.getName() + " -> " + contact.getPhoneNumber());
		}
	}
	
}
