package com.Day2_;

public class Even_Numbers {
	public static void main(String args[]) {
		int i=5,evenCount=0,oddCount=0;
		while(i<=20) {
			if(isEvenNumber(i)) {
				evenCount++;
				System.out.println(i+" is a even number");
			}else {
				oddCount++;
			}
			i++;
		}
		System.out.println("Total even numbers are "+evenCount);
		System.out.println("Total odd numbers are "+oddCount);
		
	}
	public static boolean isEvenNumber(int n) {
		if(n%2==0) {
			return true;
		}return false;
	}
}