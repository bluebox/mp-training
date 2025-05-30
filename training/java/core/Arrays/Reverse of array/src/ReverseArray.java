package com.example;

public class ReverseArray {
	public static void main(String[] args) {
		int[] a={5,6,4,3,6,4,5,7};
		int temp;
		for(int i=0;i<a.length;i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
		for(int i=0;i<a.length/2;i++) {
			temp=a[i];
			a[i]=a[a.length-i-1];
			a[a.length-1-i]=temp;
		}
		for(int i=0;i<a.length;i++) {
			System.out.print(a[i]+" ");
		}
	}
}
