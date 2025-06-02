package adv.medplus;

import java.util.Arrays;
import java.util.Random;

public class SortingArrayChallenge {
	
	public static void main(String[] args) {
		
		int[] array = new int[5];
		for(int i =0; i <5;i++) {
			array[i] = new Random().nextInt(100);
		}
		System.out.println("Array Before Sorting : "+Arrays.toString(array));
		Arrays.sort(array);int temp =0;
		for (int i =0;i<=(array.length)/2;i++) {
			temp = array[i];
			array[i] = array[array.length-1-i];
			array[array.length-1-i]=temp;
		}
		System.out.println("Array after Sorting : "+Arrays.toString(array));
	}

}
