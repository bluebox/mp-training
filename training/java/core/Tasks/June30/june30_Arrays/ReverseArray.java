package corejava.june30_Arrays;

import java.util.Scanner;

public class ReverseArray {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter size of the array");
		int n=sc.nextInt();
		int[] arr=new int[n];
		System.out.println("Enter the elements to the array");
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
		}
		System.out.println("Array before reversing");
		displayArray(arr);
		reverse(arr);
		System.out.println("Array after reversing");
		displayArray(arr);
		sc.close();
	}
	public static int[] reverse(int[] arr) {
		int i=0;
		int j=arr.length-1;
		while(i<j) {
			arr[i]+=arr[j];
			arr[j]=arr[i]-arr[j];
			arr[i]-=arr[j];
			i++;
			j--;
		}
		return arr;
	}
	
	public static void displayArray(int[] arr) {
		for(int i:arr) {
			System.out.print(i+" ");
		}
		System.out.println();
	}

}
