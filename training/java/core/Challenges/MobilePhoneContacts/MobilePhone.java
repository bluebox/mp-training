package Challenges.MobilePhoneContacts;
import java.util.ArrayList;

public class MobilePhone {
	public String myNumber;
	ArrayList<Contact>myContacts;

	public MobilePhone(String phoneNumber){
		this.myNumber=phoneNumber;
		myContacts=new ArrayList<Contact>();
	}

    public int findContact(Contact c){
		return myContacts.indexOf(c);
	}

	public boolean addNewContact(Contact c){
		if(findContact(c)>=0)
			return false;
		myContacts.add(c);
		return true;
	}

	public boolean updateContact(Contact old,Contact newc){
		int pos=findContact(old);
		if(pos>=0)
		{
			myContacts.set(pos, newc);
		}
		return false;
	}

	public int findContact(String s){
		for(int i=0;i<myContacts.size();i++)
		{
			Contact c=myContacts.get(i);
			if(c.getName().equals(s))
				return i;
		}
		return -1;
	}
	
	public boolean removeContact(Contact contact){
        int position = findContact(contact);
        if(position < 0){
            return false;
        }
        myContacts.remove(position);
        return true;
    }
    
	public Contact queryContact(String name){
        for(int i=0; i<this.myContacts.size();i++){
            Contact contact = this.myContacts.get(i);
            if(contact.getName().equals(name)){
                return contact;
            }
        }
        return null;
    }
    
    public void printContacts(){
        System.out.println("Contact List:");
        for(int i=0;i<this.myContacts.size();i++){
            Contact contact = this.myContacts.get(i);
            System.out.println(""+(i+1)+". "+contact.getName()+" -> "+contact.getPhoneNumber());
        }
    }

}