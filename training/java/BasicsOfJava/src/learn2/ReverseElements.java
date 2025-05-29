package learn2;

import java.util.Arrays;

public class ReverseElements {
	
	public static void main(String[] args) 
	{
		int[] array = {5, 6, 7, 2, 1, 9, 8, 4, 3};
		System.out.println(Arrays.toString(array));
		reverse(array);
		System.out.println(Arrays.toString(array));
		
	}
	public static void reverse(int[] array) {
		
		for(int i = 0;i <= array.length/2;i++) 
		{
			int temp = array[i];
			array[i] = array[array.length-1-i];
			array[array.length-1-i] = temp;
		}
	}
}
