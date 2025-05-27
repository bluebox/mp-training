package Methods;

import java.util.Scanner;

public class PrintEqual {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		printEqual(a,b,c);
		scanner.close();
	}
	public static void printEqual(int a,int b,int c) {
		if ((a < 0) || (b < 0) || (c < 0)){
			System.out.println("Invalid Value");
		}
		else if ((a == b) && (b == c)) {
			System.out.println("All numbers are equal");
		}
		else if ((a != b) && (b != c) && (c != a)) {
			System.out.println("All numbers are different");
		}
		else if ((a != b) || (b != c) || (c != a)) {
			System.out.println("Neither all are equal or different");
		}
	}
}
