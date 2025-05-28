package Methods;

import java.util.Scanner;

public class HasSameLastDigit {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		boolean result = hasSameLastDigit(a,b,c);
		System.out.println(result);
	}

	public static boolean hasSameLastDigit(int a, int b, int c) {
		if ((!isValid(a)) || (!isValid(b)) || (!isValid(c))) return false;
		int aLastDigit = a%10;
		int bLastDigit = b%10;
		int cLastDigit = c%10;
		if ((aLastDigit == bLastDigit) || (bLastDigit == cLastDigit) || (aLastDigit == cLastDigit)) {
			return true;
		}
		return false;
	}

	public static boolean isValid(int number) {
		return ((number >= 10) && (number <= 1000));
	}
}
