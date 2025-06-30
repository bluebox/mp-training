package day4;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {

	public static void main(String[] args) {
		int array[] = readIntegers();
		int reversedArray[] = getReverseArray(array);
		System.out.println("reversed array is " + Arrays.toString(reversedArray));
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

	private static int[] getReverseArray(int array[]) {
		int left = 0, right = array.length - 1;
		while (left < right) {
			int temp = array[left];
			array[left] = array[right];
			array[right] = temp;
			left++;
			right--;
		}
		return array;
	}
}
