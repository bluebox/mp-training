package com.tulasidhar.june30.ArraySortDesc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ArraySortDescChallenge {
	public static void main(String[] args) {
		List<Integer> intList= new ArrayList<Integer>();
		Random random = new Random();
		
		for(int i=0 ; i<10 ; i++) {
			intList.add(random.nextInt(100));
		}
		
		System.out.println("Array before sorting:");
		for(int x : intList) {
			System.out.print(x+", ");
		}
		System.out.println();
		
		Collections.sort(intList,Comparator.reverseOrder());
		System.out.println("Array after sorting:");

		for(int x : intList) {
			System.out.print(x+", ");
		}
		
	}
}
