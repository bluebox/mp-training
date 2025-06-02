package adv.medplus;

import java.util.Scanner;

public class FindMinValueChallenge {
	
	public static void main(String[] args) {
		FindMinValueChallenge fd = new FindMinValueChallenge();
		fd.readIntegers();
	}
	
	int[] array;
	public void readIntegers() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the integers by speration of commas ',' : ");
		String input = scanner.next();
		String[] str = input.split(",");
		array = new int[str.length];
		for (int i =0 ; i < str.length ; i++) {
			array[i] = Integer.parseInt(str[i]);
		}
		scanner.close();
		System.out.println("The Minimum value you entered is : "+findMin(array));
		
	}
	public int findMin(int[] array) {
		int min = array[0];
		for (int i : array) {
			if (min > i) min =i;
		}
		return min;
	}
	
}
