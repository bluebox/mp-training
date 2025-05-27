package arraypractice;

import java.util.Arrays;

public class MinimumElement {

	public static void main(String[] args) {
		int[] array= {10,0,100,5,4,8};
		
		System.out.println("Minimum element of the array is: "+minimumElement(array));

	}
	public static int minimumElement(int[] array) {
		int minimum= array[0];
		for(int i=1;i<array.length;i++) {

			if(array[i]<minimum){
				minimum=array[i];
			}
		}
		return minimum;
	}

	}


