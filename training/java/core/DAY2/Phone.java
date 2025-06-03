package training.java.core.DAY2;

import java.util.ArrayList;

public class Phone {
    public static void main(String[] args) {
        MobilePhone redmi= new MobilePhone("7794041357");
        redmi.addNewContact(Contact.createContact("ganesh","9000055555"));
        redmi.addNewContact(Contact.createContact("dad","9441281685"));
        redmi.addNewContact(Contact.createContact("mom","9640045718"));
        // System.out.println(redmi.findContact("mom"));
        // System.out.println(redmi.queryContact("ganesh"));
        redmi.printContacts();
    }
}
class MobilePhone{
    ArrayList<Contact> myContacts;
    String myNumber;
    public MobilePhone(String number){
        myNumber=number;
        myContacts= new ArrayList<>();
    }
    public boolean addNewContact(Contact contact){
        if(myContacts.contains(contact)){
            return false;
        }
        else{
            myContacts.add(contact);
            return true;
        }
    }
    public boolean updateContact(Contact con1,Contact con2){
        int idx=0;
        for(Contact c:myContacts){
            if(c.getName().equals(con1.getName())){
                myContacts.set(idx,con2);
                return true;
            }
        }
        myContacts.add(con2);
        return false;

    }
    public int findContact(Contact contact){
        int idx=0;
            for(Contact c:myContacts){
                if(c.getName()==contact.getName()&&c.getPhoneNumber()==contact.getPhoneNumber()){
                    return idx;
                }
                idx++;
            }
            return -1;
    }
    public int findContact(String name){
        int idx=0;
            for(Contact c:myContacts){
                if(c.getName().equals(name)){
                    return idx;
                }
                idx++;
            }
            return -1;
    } 
    public Contact queryContact(String name){
        for(Contact c:myContacts){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }
    public void printContacts(){
        int i=1;
        System.out.println("Contact List:");
        for(Contact c:myContacts){
            System.out.println(i+". "+c.getName()+" -> "+c.getPhoneNumber());
            i++;
        }
    }

}
class Contact{
    String name,phoneNumber;

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
    public static Contact createContact(String name,String phoneNumber){
        return new Contact(name, phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", phoneNumber=" + phoneNumber + "]";
    }
    
    
}