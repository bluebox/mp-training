package corejava.june26_Classes;

import java.util.Scanner;

public class MainClassPerson {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter first name of the person");
		String firstName=sc.nextLine();
		System.out.println("Enter last name of the person");
		String lastName=sc.nextLine();
		System.out.println("Enter age of the person");
		int age=sc.nextInt();
		PersonPoJo p=new PersonPoJo(firstName, lastName, age);
		p.setFirstName("");
		p.setLastName("");
		p.setAge(10);
		System.out.println("fullName= "+p.fullName());
		System.out.println("teen= "+p.isTeen());
		p.setFirstName("John");
		p.setAge(18);
		System.out.println("fullName= "+p.fullName());
		System.out.println("teen= "+p.isTeen());
		p.setLastName("Smith");
		System.out.println("fullName= "+p.fullName());
		sc.close();
	}

}
