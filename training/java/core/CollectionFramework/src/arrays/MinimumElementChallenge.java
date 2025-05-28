package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumElementChallenge {
	
	public static int[] readIntegers(String array)
	{
		String[] nums = array.split(",");
		int size = nums.length;
		int[] arr = new int[size];
		for(int i = 0;i<size;i++)
		{
			arr[i] = Integer.parseInt(nums[i]);
		}
		return arr;
	}
	
	public static int findMin(int...arr)
	{
		int mini = Integer.MAX_VALUE;
		for(int num:arr)
		{
			mini = mini>num?num:mini;
		}
		return mini;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the array separated by ',' with no spaces");
		String array = sc.nextLine();
		int[] arr = readIntegers(array);
		System.out.println(Arrays.toString(arr));
		int mini = findMin(arr);
		System.out.println("minimum value :: "+mini);
	}

}
