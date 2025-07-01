package corejava.june30_Arrays;

import java.util.Random;
import java.util.Scanner;

public class DescendingOrder {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Random r=new Random();
		
		//Declaring an integer array
		System.out.println("Enter the size of the Array");
		int n=sc.nextInt();
		int[] arr=new int[n];
		
		//initializing values to the array
		for(int i=0;i<n;i++) {
			arr[i]=r.nextInt(100);
		}
		
		//Displaying array before sorting
		System.out.println("Array Before Sorting");
		displayArray(arr);
		
		sortDesc(arr,n);
		
		//Displaying array after sorting
		System.out.println("Array After Sorting");
		displayArray(arr);
		
		sc.close();
	}
	public static void displayArray(int[] arr) {
		for(int i:arr) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	
	public static void sortDesc(int[] arr,int n) {
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				if(arr[i]<arr[j]) {
					arr[i]+=arr[j];
					arr[j]=arr[i]-arr[j];
					arr[i]-=arr[j];
				}
			}
		}
	}

}
