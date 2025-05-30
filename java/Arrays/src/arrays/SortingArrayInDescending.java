package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SortingArrayInDescending {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the size of array:");
		int sizeOfArray = sc.nextInt();
		System.out.println("enter the elements3"
				+ " of array:");
		ArrayList<Integer> arr = new ArrayList<>();
		int[] array= new int[sizeOfArray];
		for(int i=0; i< sizeOfArray ; i++) {
			array[i]=sc.nextInt();
			arr.add(array[i]);
		}
		System.out.println(Arrays.toString(array));
//		4ArrayList<Integer> arr = new ArrayList<>();
		
		Collections.sort(arr);
		Collections.reverse(arr);
		System.out.println(arr);
	}
}
