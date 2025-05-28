package mobilephone;
import java.util.ArrayList;

public class MobilePhone {
String myNumber;
static ArrayList<Contact> myContacts=new ArrayList<Contact>();

public MobilePhone(String phoneNumber) {
	this.myNumber=phoneNumber;
	
}

public boolean addNewContact(Contact con) {
	if(findContact(con)<0) {
		return true;
	}
	return false;
}

public boolean updateContact(Contact oldcon,Contact newcon) {
	if (findContact(oldcon)<0) {
		return false;
	}
	myContacts.remove(oldcon);
	myContacts.add(newcon);
	return true;
}

public boolean removeContact(Contact con) {
	if(findContact(con)<0) {
		return false;
	}
	myContacts.remove(con);
	return true;
}

private  static int findContact(Contact con) {
	return myContacts.indexOf(con);
}

private static int findContact(String name) {
	int i=0;
	for(Contact contact: myContacts) {
		if(contact.getName().equals(name)) {
			return i;
		}
		i=i+1;
	}
	return -1;
}

public Contact queryContact(String name) {
	int index=findContact(name);
	if(index>=0) {
		return myContacts.get(index);
	}
	return null;
}

public void printContacts() {
	for(Contact contact:myContacts) {
		System.out.println(contact.getName()+"  ---->  "+contact.getPhoneNumber());
	}
}

}
