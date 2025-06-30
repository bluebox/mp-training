package Practice.june28;

import java.util.Scanner;

public class Person {

	public static void main(String[] args) {
		System.out.println("Enter your name");
		Scanner sc=new Scanner(System.in);
		String name=sc.nextLine();
		System.out.println("Enter min balance you want to add to your account");
		double balance=sc.nextDouble();
		Bank b=new Bank(balance,name);
		System.out.println(b.getAccountName());
		System.out.println(b.getBalance());
		b.setAccountName("Deepika");
		System.out.println(b.getAccountName());
		sc.close();
	}

}
