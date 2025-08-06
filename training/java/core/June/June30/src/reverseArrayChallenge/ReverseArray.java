package reverseArrayChallenge;

import java.util.Arrays;

public class ReverseArray {
	
	public static void main(String[] args) {
		
		int array[]= {1,2,3,4,5};
		System.out.println("Initial values :");
		System.out.println(Arrays.toString(array));
		
		reverse(array);
		
		System.out.println("After reverse the array: ");
		System.out.println(Arrays.toString(array));
		
	}
	
	public static int[] reverse(int[] array) {
		int i=0;
		int j=array.length -1;
		
		while( i < j) {
			int temp = array[i];
			array[i]=array[j];
			array[j]=temp;
			i++;
			j--;
		}
		return array;
	}
}
