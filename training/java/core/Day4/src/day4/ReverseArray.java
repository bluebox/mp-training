package day4;

import java.util.Arrays;

public class ReverseArray {
	public static void main(String[] args) {
		int[] arr=new int[]{1,2,3,4,5};
		
		arr=reverseArr(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static int[] reverseArr(int[] num) {
		//System.out.println(Arrays.toString(num));
		for(int i=0;i<num.length/2;i++) {
			int temp=num[i];
			num[i]=num[num.length-1-i];
			num[num.length-1-i]=temp;
		}
		//System.out.println(Arrays.toString(num));
		return num;
	}
}
