package Arrays;

import java.util.Random;
import java.util.*;
public class Array {

	public static void main(String[] args) {

		int[] arr= new int[10];
		int[] temp= new int[10];
		Random random = new Random(100);
		for (int i = 0; i < 10; i++) {
			arr[i] = random.nextInt(10);
		}

		System.out.println("Before sorting :: ");
		int i = 0;
		for (int num : arr) {
			System.out.print("" + num + " ");
			temp[i++] = num;
		}

		Arrays.sort(arr);
		System.out.println("\nAfter Sorting :: ");
		for (int num : arr) {
			System.out.print("" + num + " ");
		}
	}

}