package ContactChallenge;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> phoneData = List.of(
                "Charlie Brown,3334445555",
                "Maid Marion,1234567890",
                "Mickey Mouse,9998887777",
                "Mickey Mouse,1247489758",
                "Minnie Mouse,4567805666",
                "Robin Hood,5647893000",
                "Robin Hood,7899028222",
                "Lucy Van Pelt,5642086852",
                "Mickey Mouse,9998887777"
        );

        List<String> emailData = List.of(
                "Mickey Mouse,mckmouse@gmail.com",
                "Mickey Mouse,micky1@aws.com",
                "Minnie Mouse,minnie@verizon.net",
                "Robin Hood,rhood@gmail.com",
                "Linus Van Pelt,lvpelt2015@gmail.com",
                "Daffy Duck,daffy@google.com"
        );

        Map<String, Contact> contacts = new HashMap<>();

        for (String data : phoneData) {
            String[] parts = data.split(",");
            String name = parts[0].trim();
            long phone = Long.parseLong(parts[1].trim());
            contacts.putIfAbsent(name, new Contact(name));
            contacts.put(name, contacts.get(name).mergeContactData(new Contact(name, phone)));
        }

        for (String data : emailData) {
            String[] parts = data.split(",");
            String name = parts[0].trim();
            String email = parts[1].trim();
            contacts.putIfAbsent(name, new Contact(name));
            contacts.put(name, contacts.get(name).mergeContactData(new Contact(name, email)));
        }

        for (Contact contact : contacts.values()) {
            System.out.println(contact);
        }
    }
}

class Contact {
    String name;
    Set<String> emails = new HashSet<>();
    Set<String> phones = new HashSet<>();

    public Contact(String name) {
        this.name = name;
    }

    public Contact(String name, String email) {
        this(name, email, 0L);
    }

    public Contact(String name, long phone) {
        this(name, null, phone);
    }

    public Contact(String name, String email, long phone) {
        this.name = name;
        if (email != null) {
            emails.add(email);
        }
        if (phone != 0) {
            String phoneFormatted = String.format("(%03d) %03d-%04d",
                    phone / 10000000, (phone / 10000) % 1000, phone % 10000);
            phones.add(phoneFormatted);
        }
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Name: " + name + "\nEmails: " + emails + "\nPhones: " + phones + "\n";
    }

    public Contact mergeContactData(Contact contact) {
        Contact newContact = new Contact(this.name);
        newContact.emails.addAll(this.emails);
        newContact.phones.addAll(this.phones);
        if (contact != null) {
            newContact.emails.addAll(contact.emails);
            newContact.phones.addAll(contact.phones);
        }
        return newContact;
    }
}

