package ArrayList;

import java.util.ArrayList;
import java.util.Arrays;

public class mobileTest {
	
	public static void main(String[] args) {
		
		Contacts c = Contacts.createContact("ram", "1234567890");
		Contacts c1 = Contacts.createContact("rahim", "23434655778");
		ArrayList<Contacts>array = new ArrayList<>(Arrays.asList(c,c1));
		
		MobilePhone phone = new MobilePhone("7993502434", array);
		
		System.out.println(phone.findContact("ram"));
		System.out.println(phone.findContact(c));
		phone.printContacts();
	}
}
