package Practice.june25;

import java.util.Scanner;

/*Write a method isEven(int num) that returns true if the number is even, otherwise false.
Use this method in main to check a number and print "Even" or "Odd" using an if statement.*/
public class EvenOrOdd {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number");
		int n=sc.nextInt();
		if(isEven(n)) {
			System.out.println("Even");
		}
		else {
			System.out.println("Odd");
		}
		sc.close();
	}
	public static boolean isEven(int n) {
		return (n & 1) == 0;
	}
}
