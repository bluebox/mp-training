package com.tulasidhar.june30.MinimumElementChallenge;

import java.util.ArrayList;
import java.util.Scanner;

public class MinimumElement {
	public static void main(String[] args) {
		ArrayList<Integer> arr = readIntegers();
		System.out.println(arr);
		System.out.println("Minimum Value:"+minimumElement(arr));
	}
	
	static ArrayList<Integer> readIntegers() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String chunks[] = input.split(",");
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(String chunk : chunks) {			
			System.out.println(chunk);
			chunk = chunk.replaceAll(" ","");
			result.add(Integer.parseInt(chunk));
		}
		return result;
 	}
	static int minimumElement(ArrayList<Integer> arr) {
		int min = Integer.MAX_VALUE;
		
		for(int x : arr) {
			if(x<min) {
				min = x;
			}
		}
		return min;
	}
}
