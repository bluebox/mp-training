package day4;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumElement {
	public static void main(String[] args) {
		int numbers[] = readIntegers();
		System.out.println(Arrays.toString(numbers));
		int minValue = Integer.MAX_VALUE;
		for (int i : numbers) {
			minValue = Math.min(minValue, i);
		}
		System.out.println("Minimum value is " + minValue);
	}

	private static int[] readIntegers() {
		Scanner scanner = new Scanner(System.in);
		String[] tokens = scanner.nextLine().split(",");
		int numbers[] = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			numbers[i] = Integer.parseInt(tokens[i]);
		}
		return numbers;
	}
}
