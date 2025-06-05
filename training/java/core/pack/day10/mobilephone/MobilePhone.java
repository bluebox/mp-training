package day10.mobilephone;

import java.util.ArrayList;

public class MobilePhone {
 private String myNumber;
 private ArrayList<Contacts>arr;
 MobilePhone(String myNumber){
	 this.myNumber=myNumber;
	 this.arr=new ArrayList<Contacts>();
 }
 public boolean addNewContact(Contact c) {
    return !arr.contains(c);
 }
 public boolean updateContact(Contact oldContact ,Contact newContact) {
	 
 }
}
