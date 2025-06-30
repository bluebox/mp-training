package com.day4_;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Array {

	public static void main(String[] args) {
       // Integer[] numbers = {50, 25, 80, 5, 15};
    	Integer[] numbers = new Integer[10];
    
    	
    	Random random  =new Random();
    	for (int i = 0; i < 10; i++){
    		numbers[i] = random.nextInt(100);
			
		}
        System.out.println("Original array:");
        System.out.println(Arrays.toString(numbers));

        Arrays.sort(numbers, Collections.reverseOrder());

        System.out.println("Sorted array in descending order:");
        System.out.println(Arrays.toString(numbers));
    }
}