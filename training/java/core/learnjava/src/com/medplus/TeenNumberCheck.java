package com.medplus;

import java.util.Arrays;
import java.util.Scanner;

public class TeenNumberCheck {
	public static boolean hasTeen(int value1,int value2 , int value3) {
		return (isTeen(value1)||isTeen(value2)||isTeen(value3));
	}
	public static boolean isTeen(int value) {
		if (value >=13 && value <=19) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] value = new int[3];
		for (int i=1;i<4;i++) {
			System.out.print("\nEnter value"+i+" :");
			value[i-1] = scanner.nextInt();
		
		}
		
		System.out.println(Arrays.toString(value) + " has a teen :"+hasTeen(value[0],value[1],value[2]));
		scanner.close();
	}

}
