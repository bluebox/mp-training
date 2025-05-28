package Methods;

import java.util.Scanner;

public class HasTeenBool {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		boolean result = hasTeen(a,b,c);
		System.out.println(result);
		scanner.close();
	}
	public static boolean hasTeen(int a,int b,int c) {
		return (isTeen(a) || isTeen(b) || isTeen(c));
	}
	public static boolean isTeen (int number) {
		return ((number >= 13) && (number <= 19));
	}
}
