package com.example;
public class MinArray {
	public static void main(String[] args) {
		int[] a= {5,4,3,2,1};
		System.out.println(findMin(a));
	}
	public static int findMin(int[] a) {
		int x=a[0];
		for(int i:a) {
			if(x>i) {
				x=i;
			}
		}
		return x;
	}
}
