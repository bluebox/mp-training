package arraypractice;

import java.util.Scanner;
import java.util.Arrays;

public class SortDescending {

	public static void main(String[] args) {
		int[] array= getIntegers(6);
		
		
		System.out.println("Original array is: "+Arrays.toString(array));
		int[] arraySorted= sortArrayDescending(array);
		System.out.println("Sorted array in descending order is: "+Arrays.toString(arraySorted));
		

	}
	public static int[] sortArrayDescending(int[] array) {
		int[] sortd=new int[array.length];
		Arrays.sort(array);
		for(int i=0;i<array.length;i++) {
			sortd[i]=array[array.length-i-1];
		}
		
		return sortd;
	}
	public static int[] getIntegers(int count) {
		int[]array= new int[count];
		System.out.println("Enter "+count+" integers: ");
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<count;i++) {
			array[i]=sc.nextInt();
		}
		return array;

	}

}
