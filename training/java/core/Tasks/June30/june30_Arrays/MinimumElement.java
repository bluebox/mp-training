package corejava.june30_Arrays;

import java.util.Scanner;

public class MinimumElement {

	public static void main(String[] args) {
		int[] myArray=readIntegers();
		System.out.println("Given array is: ");
		displayArray(myArray);
		int minimum=findMin(myArray);
		System.out.println("The minimum number in the given array is :"+minimum);
	}
	
	public static int[] readIntegers() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the elements of the array seperating with ','");
		String[] values=sc.nextLine().split(",");
		int[] arr=new int[values.length];
		for (int i = 0; i < values.length; i++) {
			arr[i] = Integer.parseInt(values[i].trim());
		}
		sc.close();
		return arr;
	}
	
	public static int findMin(int[] arr) {
		int min=arr[0];
		for(int i=1;i<arr.length;i++) {
			if(min>arr[i])
				min=arr[i];
		}
		return min;
	}
	
	public static void displayArray(int[] arr) {
		for(int i:arr) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
}
