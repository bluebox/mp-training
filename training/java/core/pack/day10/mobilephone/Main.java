package day10.mobilephone;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MobilePhone mp=new MobilePhone("7337584295");
		Contacts c1=new Contacts("Bob","31415926");
		Contacts c2=new Contacts("Alice","16180339");
		mp.addNewContact(c1);
		mp.addNewContact(c2);
		mp.addNewContact(new Contacts("Tom","11235813"));
		mp.addNewContact(new Contacts("Jane","23571113"));
		
		mp.printContacts();
		System.out.println("---------");
		mp.updateContact(c2, new Contacts("sammer","9490259196"));
		mp.printContacts();
		System.out.println("---------");
		mp.removeContact(c1);
		mp.printContacts();
		System.out.println("---------");
		mp.queryContact("sammer");
		mp.printContacts();
		System.out.println("---------");
		
		mp.addNewContact(Contacts.createContact("srisai","6354214897"));
		mp.printContacts();
		
		
	}

}
