
package Arrays;

import java.util.Random;
import java.util.*;
public class ReversinganArray {

	public static void main(String[] args) {

		int[] arr= new int[10];
		Random random = new Random(100);
		for (int i = 0; i < 10; i++) {
			arr[i] = random.nextInt(10);
		}
		
		Random random1 = new Random(1);
		System.out.println(random1.nextInt()); // Always gives the same result

		Random random2 = new Random(1);
		System.out.println(random2.nextInt()); // Same as above

		

		Arrays.sort(arr);
		System.out.println("\nAfter Sorting :: ");
		for (int num : arr) {
			System.out.print("" + num + " ");
		}
		System.out.println();
		reverseArray(arr);
		
		System.out.println("Array After Reversing:");
		System.out.println(Arrays.toString(arr));
	}
	public static void reverseArray(int[] arr)
	{
		int n=arr.length;
		for(int i = 0;i<n/2;i++)
		{
			int temp = arr[n-1-i];
			arr[n-1-i]= arr[i];
			arr[i] = temp;
		}
	}
}
