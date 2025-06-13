package ArrayListChallenge;
import java.util.*;
class MobilePhone{
	public String myNumber;
	public ArrayList<Contact> ans;
	public MobilePhone(String number) {
		this.myNumber=number;
		this.ans=new ArrayList<>();
	}
	public boolean addNewContact(Contact contact) {
		 if(this.ans.contains(contact))return false;
		 this.ans.add(contact);
		 return true;
	}
	public boolean updateContact(Contact oldcontact,Contact newcontact) {
		if(this.ans.contains(oldcontact)) {
		    this.ans.remove(oldcontact);
		    this.ans.add(newcontact);
		    return true;
		}
		return false;
	}
	public boolean removeContact(Contact contact) {
		if(this.ans.contains(contact)) {
			this.ans.remove(contact);
			return true;
		}
		return false;
	}
	public int findContact(Contact contact) {
		return this.ans.indexOf(contact);
	}
	public int findContact(String names) {
		for(int i=0;i<this.ans.size();i++) {
			Contact ele=this.ans.get(i);
			if(ele.name==names)return i;
		}
		return -1;
	}
	public Contact queryContact(String name) {
		for(Contact ele:this.ans) {
			if(ele.name==name)return ele;
		}
		return null;
	}
	public void printContact() {
		if(this.ans.size()==0) {
			System.out.println("nothing to print");
			return ;
		}
		for(Contact ele:this.ans) {
			System.out.println(ele.name+"->"+ele.phoneNumber);
		}
	}
}
class Contact{
	public String name;
	public String phoneNumber;
	public Contact(String name,String phoneNumber) {
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	public String getName() {
		return this.name;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public static Contact createContact(String name,String phoneNumber) {
		Contact ans=new Contact(name,phoneNumber);
		return ans;
	}
}
public class PhoneContact {

	public static void main(String[] args) {
	    Contact anand=Contact.createContact("anand", "9701674944");
	    MobilePhone abhi=new MobilePhone("abhi");
	    if(abhi.addNewContact(anand)) {
	    	System.out.println("added new contact");
	    }
	    else System.out.println("contact already exist");
	    if(abhi.addNewContact(anand)) {
	    	System.out.println("added new contact");
	    }
	    else System.out.println("contact already exist");
	    abhi.printContact();
	    abhi.removeContact(anand);
	    abhi.printContact();
	    abhi.addNewContact(anand);
	    Contact naresh=Contact.createContact("naresh", "9100628281");
	    abhi.updateContact(anand, naresh);
	    abhi.printContact();
	}

}
