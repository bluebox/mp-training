package June30;

import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class ArrayChallenge {
	public static void main(String[] args) {
		int[] arr=new int[5];
		Random random = new Random();
		for(int i=0;i<5;i++) {
			arr[i]=random.nextInt(100);
		}
		System.out.println("Array Before Sorting : " + Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("Array After Sorting (Ascending) : " + Arrays.toString(arr));
		//Arrays.sort(arr,Collections.reverseOrder()); //this method works on Integer not on int
		for(int i=0;i<2;i++) {
			int temp = arr[i];
			arr[i]=arr[4-i];
			arr[4-i]=temp;
		}
		System.out.println("Array After Sorting (Ascending) : " + Arrays.toString(arr));
	}
}
