package Methods;

import java.util.Scanner;

public class DigitSum {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		int result = digitSum(number);
		if (result == -1) {
			System.out.println("Invalid value");
			return;
		}
		System.out.println("Digit sum of "+number+" is "+result);
	}

	public static int digitSum(int number) {
		if (number < 0) return -1;
		else if (number >=0 && number <= 9) return number;
		int result = 0;
		while (number > 0) {
			result += number%10;
			number /= 10;
		}
		return result;
	}
	
}
