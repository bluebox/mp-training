package challenges_30th_june;

import java.util.Arrays;
import java.util.Scanner;

public class MininmumElementChallenge {
	
	public static int[] readIntegers(String s) {
		String[] arr=s.split(",");
		int arr1[]=new int[arr.length];
		for(int i=0;i<arr.length;i++) {
			arr1[i]=Integer.parseInt(arr[i].trim());
		}
		return arr1;
	}
	public static int findMin(int arr[]) {
		int minVal=arr[0];
		for(int x:arr) {
			minVal=Math.min(minVal, x);
		}
		return minVal;
	}
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter number separated by ,");
		String s=sc.nextLine();
		int ar[]=readIntegers(s);
		System.out.println(Arrays.toString(ar));
		System.out.println(findMin(ar));
	}
}
