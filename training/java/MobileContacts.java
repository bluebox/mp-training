import java.util.*;

public class MobileContacts {
	
		public static boolean findContact(ArrayList<String[]> ContactList, String name) {
			for(int i=0;i<ContactList.size();i++) {
				if(ContactList.get(i)[0].equals(name)) return true;
			}
			return false;
		}
		
		public static int findContactIndex(ArrayList<String[]> ContactList, String name) {
			for(int i=0;i<ContactList.size();i++) {
				if(ContactList.get(i)[0].equals(name)) return i;
			}
			return -1;
		}
		
		public static void createContact(ArrayList<String[]> ContactList, String name, String number) {
			if(!findContact(ContactList,name)) {
				ContactList.add(new String[] {name,number});
				System.out.println("Contact created!");
			}
			else {
				System.out.println("Contact Already Exists");
			}
		}
		
		public static void updateContact(ArrayList<String[]> ContactList, String name, String number) {
			if(findContact(ContactList,name)) {
				int idx=findContactIndex(ContactList,name);
				ContactList.set(idx,new String[] {name,number});
				System.out.println("Contact updateded!");
			}
			else {
				System.out.println("Contact Doesnot Exists");
			}
			
		}
		public static void removeContact(ArrayList<String[]> ContactList, String name) {
			if(findContact(ContactList,name)) {
				int idx=findContactIndex(ContactList,name);
				ContactList.remove(idx);
				System.out.println("Contact removed!");
			}
			else {
				System.out.println("Contact Doesnot Exists");
			}
			
		}
		public static void printContacts(ArrayList<String[]> ContactList) {
			System.out.println("Contact List: ");
			for(int i=0;i<ContactList.size();i++) {
				System.out.println(i+1+". "+ContactList.get(i)[0]+" -> "+ContactList.get(i)[1]);
			}
		}
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String[]> ContactList = new ArrayList<>();
		createContact(ContactList, "Ram", "0123456789");
		createContact(ContactList,"Sameer","9876543210");
		createContact(ContactList, "Ravi", "1123456789");
		createContact(ContactList, "Shyam", "1231231239");
		createContact(ContactList,"Anurag","1234123443");
		createContact(ContactList, "Mourya", "1123456789");
		createContact(ContactList, "Ranjith", "0987656789");
		createContact(ContactList,"Rohith","1234554321");
		createContact(ContactList, "Aravind", "1876543229");
		printContacts(ContactList);
		removeContact(ContactList, "Ravi");
		updateContact(ContactList,"Ram", "9999999999");
		createContact(ContactList, "Aravind", "1876543229");
		printContacts(ContactList);
	
	}

}
