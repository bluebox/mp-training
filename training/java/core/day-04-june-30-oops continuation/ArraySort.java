package day4;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ArraySort {

	public static void main(String[] args) {
		int size = 5;
		Integer[] array = getRandomArray(5);
		System.out.println(Arrays.toString(array));
		Arrays.sort(array,Collections.reverseOrder());
		System.out.println(Arrays.toString(array));
	}
	private static Integer[] getRandomArray(int size) {
		Random random = new Random();
		Integer array[] = new Integer[size];
		for(int i=0;i<size;i++) 
			array[i] = random.nextInt(100);
		return array;
	}
}
