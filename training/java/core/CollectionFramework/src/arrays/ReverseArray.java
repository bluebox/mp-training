package arrays;

import java.util.Scanner;

public class ReverseArray {
	
	public static void reverse(int[] arr)
	{
		for(int i = 0;i<arr.length/2;i++)
		{
			int temp = arr[arr.length-1-i];
			arr[arr.length-1-i]= arr[i];
			arr[i] = temp;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the elements of the array separated by ',' ");
		String array = sc.nextLine();
		
		String[] nums = array.split(",");
		int[] arr = new int[nums.length];
		
		for(int i = 0;i<arr.length;i++)
		{
			arr[i] = Integer.parseInt(nums[i]);
		}
		reverse(arr);
		
		System.out.println("After Reversal :: ");
		for(var num:arr)
		{
			System.out.print(""+num+" ");
		}
	}
}
