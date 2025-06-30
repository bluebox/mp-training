package June30;

import java.util.Arrays;
import java.util.Random;

public class ReverseArray {
	public static void main(String[] args) {
		int[] arr=getRandomArr(17);
		System.out.println("Array : " + Arrays.toString(arr));
		reverseArr(arr);
		System.out.println("Reverse Array : " + Arrays.toString(arr));
	}
	public static int[] getRandomArr(int n) {
		int[] arr=new int[n];
		Random random = new Random();
		for(int i=0;i<n;i++) {
			arr[i]=random.nextInt(100);
		}
		return arr;
	}
	public static void reverseArr(int[] arr) {
		for(int i=0;i<arr.length/2;i++) {
			int temp=arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=temp;
		}
	}
}
