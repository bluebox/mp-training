package corejava.june26_conditionalstatements;

import java.util.Scanner;

public class CraftingAPrinter {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a value");
		int a=sc.nextInt();
		System.out.println("Enter b value");
		int b=sc.nextInt();
		System.out.println("Enter c value");
		int c=sc.nextInt();
		printEqual(a,b,c);
		sc.close();
	}
	public static void printEqual(int a,int b,int c) {
		if(a<0 ||b<0||c<0) {
			System.out.println("Invalid Value");
		}
		else if(a==b && b==c) {
			System.out.println("All numbers are equal");
		}
		else if(a!=b && b!=c && a!=c) {
			System.out.println("All numbers are different!");
		}
		else {
			System.out.println("Neighter all are equal or different");
		}
	}
}
