package Methods;

import java.util.Scanner;

public class HasTeen {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int firstNumber = scanner.nextInt();
		int secondNumber = scanner.nextInt();
		int thirdNumber = scanner.nextInt();
		boolean result = hasTeen(firstNumber, secondNumber, thirdNumber);
		System.out.println(result);
		scanner.close();
	}
	public static boolean hasTeen(int firstNumber, int secondNumber,int thirdNumber) {
		if (firstNumber >= 13 && firstNumber <= 19) {
			return true;
		}
		else if (secondNumber >= 13 && secondNumber <= 19) {
			return true;
		}
		else if (thirdNumber >= 13 && thirdNumber <= 19) {
			return true;
		}
		return false;
	}
}
