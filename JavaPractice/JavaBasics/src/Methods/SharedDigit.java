package Methods;

import java.util.Scanner;

public class SharedDigit {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		boolean result = hasSharedDigit(a,b);
		System.out.print(result);
	}

	public static boolean hasSharedDigit(int a, int b) {
		if ((!isRange(a)) || (!isRange(b))) return false;
		int aFirstDigit = a/10;
		int aLastDigit = a%10;
		int bFirstDigit = b/10;
		int bLastDigit = b%10;
		return (isSharing(aFirstDigit,bFirstDigit,bLastDigit) || 
				isSharing(aLastDigit,bFirstDigit,bLastDigit));
	}

	public static boolean isSharing(int ax, int bx, int by) {
		return ((ax == bx) || (ax == by));
	}

	public static boolean isRange(int number) {
		return ((number >= 10) && (number <= 99));
	}
}
