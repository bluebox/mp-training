import java.util.LinkedList;
import java.util.ListIterator;

public class MobilePhoneContactsUsingLinkedList {
    public static void main(String[] args) {
        MobilePhone my = new MobilePhone("PRASAD");
        my.addContact("VAMSI", "123456");
        my.addContact("SWATHI", "33435647");
        my.addContact("RAJU", "5454926");
        my.addContact("LIKITHA", "242563424");

        my.printContacts();
        System.out.println("____________________________________");

        Contact a = new Contact("SWATHI", "33435647");
        System.out.println("the contact SWATHI exists: " + my.checkContact(a));
        System.out.println("____________________________________");
        System.out.println("Index of SWATHI: " + my.findContact(a));
        System.out.println("____________________________________");
        System.out.println("Index of contact name 'SWATHI': " + my.findContact("SWATHI"));
        System.out.println("____________________________________");

        Contact b = new Contact("SWATHI", "0000000000");
        System.out.println("Updated contact number: " + b.getPhoneNumber());

        if (my.updateContact(a, b)) {
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Contact update failed.");
        }

        System.out.println("After updating:");
        my.printContacts();

        if (my.removeContact(new Contact("VAMSI", "123456"))) {
            System.out.println("Contact removed successfully.");
        } else {
            System.out.println("Contact not found to remove.");
        }

        System.out.println("After removing:");
        my.printContacts();

        a = my.queryContact("VAMSI");
        if (a != null) {
            System.out.println(a.getName() + " -------> " + a.getPhoneNumber());
        } else {
            System.out.println("Contact 'VAMSI' not found.");
        }
    }

    public static class MobilePhone {
        private String myNumber;
        private LinkedList<Contact> myContacts;

        public MobilePhone(String myNumber) {
            this.myNumber = myNumber;
            this.myContacts = new LinkedList<>();
        }

        public void addContact(String name, String phoneNumber) {
            Contact newContact = new Contact(name, phoneNumber);
            myContacts.addLast(newContact);
        }

        public boolean checkContact(Contact contact) {
            return myContacts.contains(contact);
        }

        public boolean updateContact(Contact oldContact, Contact newContact) {
            ListIterator<Contact> iter = myContacts.listIterator();
            while (iter.hasNext()) {
                Contact current = iter.next();
                if (current.equals(oldContact)) {
                    iter.set(newContact);
                    return true;
                }
            }
            return false;
        }

        public boolean removeContact(Contact contact) {
            return myContacts.remove(contact);
        }

        public int findContact(Contact contact) {
            ListIterator<Contact> iter = myContacts.listIterator();
            int index = 0;
            while (iter.hasNext()) {
                Contact current = iter.next();
                if (current.equals(contact)) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        public int findContact(String contactName) {
            ListIterator<Contact> iter = myContacts.listIterator();
            int index = 0;
            while (iter.hasNext()) {
                Contact current = iter.next();
                if (current.getName().equals(contactName)) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        public Contact queryContact(String contactName) {
            for (Contact contact : myContacts) {
                if (contact.getName().equals(contactName)) {
                    return contact;
                }
            }
            return null;
        }

        public void printContacts() {
            for (Contact contact : myContacts) {
                System.out.println(contact.getName() + " -> " + contact.getPhoneNumber());
            }
        }
    }

    public static class Contact {
        private String name;
        private String phoneNumber;

        public Contact(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public static Contact createContact(String personName, String personNumber) {
            return new Contact(personName, personNumber);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Contact contact = (Contact) obj;
            return name.equals(contact.name) && phoneNumber.equals(contact.phoneNumber);
        }

        @Override
        public int hashCode() {
            return name.hashCode() + phoneNumber.hashCode();
        }
    }
}
