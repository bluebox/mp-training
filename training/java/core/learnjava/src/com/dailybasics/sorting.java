package com.dailybasics;

import java.util.Arrays;
import java.util.Scanner;

public class sorting {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
//		System.out.println("Enter numbers with space separation : ");
//		String input = scanner.nextLine();
//		String[] arr = input.trim().split("\\s+");
//		try {
//			int[] val = new int[arr.length];
//			for (int i = 0; i < arr.length; i++) {
//				val[i] = Integer.parseInt(arr[i]);
//			}
//			int ans[] = new int[arr.length];
//			Arrays.sort(val);
//			for (int i = 0; i < (val.length) / 2; i++) {
//				ans[i * 2] = val[val.length - 1 - i];
//				ans[i * 2 + 1] = val[i];
//			}
//			if (val.length % 2 != 0) {
//				ans[val.length - 1] = val[((val.length) / 2)];
//			}
//			System.out.println(Arrays.toString(ans));
//		}catch(Exception e) {
//			System.out.println("Enter only numbers");
//		}
//		scanner.close();
		
		
		int[] val = new int[] { 6, 2, 4, 9, 5 };
		int ans[] = new int[val.length];
		Arrays.sort(val);
		for (int i = 0; i < (val.length) / 2; i++) {
			ans[i * 2] = val[val.length - 1 - i];
			ans[i * 2 + 1] = val[i];
		}
		if (val.length % 2 != 0) {
			ans[val.length - 1] = val[((val.length) / 2)];
		}
		System.out.println(Arrays.toString(ans));
	}

}
