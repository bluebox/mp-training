package day10.sortarray;

import java.util.Arrays;
import java.util.Scanner;

public class SortArray {
public int[] getIntegers(int n) {
	Scanner sc=new Scanner(System.in);
	int arr[]=new int[n];
	for(int i=0;i<n;i++)
		arr[i]=sc.nextInt();
	return arr;
}
public int[] sortIntegers(int arr[]) {
	for(int i=0;i<arr.length;i++){
		for(int j=0;j<arr.length-1-i;j++) {
			if(arr[j]<arr[j+1]) {
				int temp=arr[j+1];
				arr[j+1]=arr[j];
				arr[j]=temp;
			}
		}
	}
	return arr;
}
public void printIntegers(int arr[]) {
	for(int a:arr)
		System.out.print(a+" ");
}
}
