package day10.mobilephone;

import java.util.ArrayList;

public class MobilePhone {
 private String myNumber;
 private ArrayList<Contacts>arr;
 MobilePhone(String myNumber){
	 this.myNumber=myNumber;
	 this.arr=new ArrayList<Contacts>();
 }
 public boolean addNewContact(Contacts c) {
	if(findContact(c)!=-1)
		return false;
	this.arr.add(c);
	return true;
    
 }
 public boolean updateContact(Contacts oldContact ,Contacts newContact) {
	 int index=findContact(oldContact);
	 if(index==-1)
		 return false;
	 this.arr.set(index,newContact);
	 return true;
 }
 public boolean removeContact(Contacts c) {
	 int index=findContact(c);
	 if(index==-1)
		 return false;
	 this.arr.remove(index);
	 return true;
 }
 
 private int findContact(Contacts contact) {
	 return this.arr.indexOf(contact);
 }
 private int findContact(String name) {
	 for(int i=0;i<this.arr.size();i++) 
		 if(arr.get(i).getName().equals(name))
			 return i;
	 return -1;
	 
 }
 public Contacts queryContact(String name) {
	 int index=findContact(name);
	 if(index==-1)
		 return null;
	 return this.arr.get(index);
 }
 public void  printContacts() {
	 for(Contacts c:this.arr)
		 System.out.println(c.getName()+" -> "+c.getPhoneNumber());
 }
 
}
