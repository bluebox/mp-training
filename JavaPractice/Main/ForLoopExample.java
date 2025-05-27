package Main;

import java.util.Scanner;

public class ForLoopExample {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a number to display its table");
		int number = scanner.nextInt();
		for (int itr = 1;itr <= 10;itr++) {
			System.out.println(number+" x "+itr+" = "+(number*itr));
		}
		scanner.close();
	}
}
