package learn2;

import java.util.Arrays;

public class SortArray {
	
	public static void main(String[] args) {
		
		int[] array = new int[10];
		for(int i: array) {
			System.out.println(i + " ");
		}
		System.out.println(Arrays.toString(array));
		
		
		int[] array2 = {6, 3, 2 ,1 ,4};
		System.out.println(Arrays.toString(array));
		
		Arrays.sort(array2);
		
		System.out.println(Arrays.toString(array2));
	}
}
