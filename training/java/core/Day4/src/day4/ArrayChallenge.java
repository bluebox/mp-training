package day4;

import java.util.Arrays;
import java.util.Random;

public class ArrayChallenge {
	public static void main(String[] args) {
	int[] arr=randomArray(5);
	System.out.println("array before sorting : "+Arrays.toString(arr));
	Arrays.sort(arr);
	for(int i=0;i<arr.length/2;i++) {
		int temp=arr[i];
		arr[i]=arr[arr.length-1-i];
		arr[arr.length-1-i]=temp;
	}
	System.out.println("array after sorting in descending order : "+Arrays.toString(arr));
	
}

public static int[] randomArray(int n) {
	Random random=new Random();
	int[] arr=new int[n];
	for(int i=0;i<n;i++) {
		arr[i]=random.nextInt(100);
	}
	return arr;
}
}