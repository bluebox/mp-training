package june26_methods;
import java.util.Scanner;

import june26_Constructors.Customer;
public class Person {
	private String firstname;
	private String lastname;
	private int age;
	
	public String getFirstName() {
		return ("First name : "+firstname);
	}
	
	public String getLastName() {
		return ("Last Name : "+lastname);
		
	}
	
	public int getAge() {
		return age;
	}
	
	public void setFirstName(String fname)
	{
		firstname=fname;
	}	
	
	public void setLastName(String lname) {
		lastname=lname;
	}
	
	public void setAge(int age) {
		if(age>0 && age<100) {
			this.age=age;
		}
	}
	
	public boolean isTeen() {
		return (age>12 && age<20);
	}
	
	public String getFullName() {
		if(firstname.isEmpty() && lastname.isEmpty())
			return "";
		else if(firstname.isEmpty())
			return lastname;
		else
			return firstname;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1=new Person();
		p1.setFirstName("greshma");
		p1.setLastName("ganta");
		p1.setAge(21);
		System.out.println("First name: "+p1.getFirstName());
		System.out.println("Second name: "+p1.getLastName());
		System.out.println("Age: "+p1.getAge());
		System.out.println("Is the person Teen :"+p1.isTeen());
		System.out.println("full name of person :"+p1.getFullName());

		

	}

}
