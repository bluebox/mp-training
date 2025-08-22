package com.prana;

public class Mainjpa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" JPA ");
		JPA1 jpa1 = new JPA1();
		jpa1.setId(1);
		jpa1.setName("Prana");
		//System.out.println("\n");
		//System.out.println("Rope is about to break, but it is not");
		System.out.println("\nStudent Details :");
		System.out.println("\nStudent ID : "+ jpa1.getId() + "\n"+"Student Name : "+jpa1.getName());
	}

}
