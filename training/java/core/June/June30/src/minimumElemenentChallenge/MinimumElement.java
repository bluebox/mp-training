package minimumElemenentChallenge;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumElement {
	public static void main(String[] args) {
		int[] array = readIntegers();
 
		System.out.println(Arrays.toString(array));
		
		int minElement = findMin(array);
		System.out.println("Min Element is "+minElement);
	}
	
	public static int[] readIntegers() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter numbers separated by commas: ");
		String input = scanner.nextLine();
		String[] tempArray = input.split(",");
		
		int[] array = new int[tempArray.length];
		
		for(int i=0;i<tempArray.length;i++) {
			array[i] = Integer.parseInt(tempArray[i]);
		}
		
		scanner.close();
		return array;
	}
	
	public static int findMin(int[] array) {
		int min = array[0];
		
		for(int i=1;i<array.length;i++) {
			if(min > array[i]) {
				min = array[i];
			}
		}
		return min;
	}
	
}
