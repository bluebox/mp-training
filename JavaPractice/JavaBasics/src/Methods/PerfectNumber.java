package Methods;

import java.util.Scanner;

public class PerfectNumber {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		boolean result = hasPerfectNumber(number);
		System.out.println(result);
	}
	public static boolean hasPerfectNumber(int number) {
		if (number < 0) return false;
		int sum = 1;
		for (int i = 2;i < 6;i++) {
			if (number%i == 0) sum += i;
		}
		return (sum == number);
	}
}
