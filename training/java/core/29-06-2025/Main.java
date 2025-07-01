public class Main {
    public static void main(String[] args) {
        MobilePhone phone = new MobilePhone("9876543210");

        Contact c1 = Contact.createContact("Bob", "31415926");
        Contact c2 = Contact.createContact("Alice", "16180339");
        Contact c3 = Contact.createContact("Tom", "11235813");
        Contact c4 = Contact.createContact("Jane", "23571113");

        phone.addNewContact(c1);
        phone.addNewContact(c2);
        phone.addNewContact(c3);
        phone.addNewContact(c4);

        phone.printContacts();

        System.out.println("Updating Alice...");
        phone.updateContact(c2, Contact.createContact("Alice", "99999999"));

        phone.printContacts();

        System.out.println("Removing Bob...");
        phone.removeContact(c1);

        phone.printContacts();

        System.out.println("Query Tom: " + phone.queryContact("Tom").getPhoneNumber());
        System.out.println("Query Unknown: " + phone.queryContact("Unknown"));
    }
}
