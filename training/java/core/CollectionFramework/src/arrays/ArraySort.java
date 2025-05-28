package arrays;

import java.util.Arrays;
import java.util.Random;

public class ArraySort {
	
	public static void main(String[] args) {
		int[] arrayNumbers = new int[10];
		int[] dupArrayNumbers = new int[10];
		Random random = new Random(100);
		for(int i = 0;i<10;i++)
		{
			arrayNumbers[i] = random.nextInt(10);
		}
		
		System.out.println("Before sorting :: ");
		int i = 0;
		for(int num: arrayNumbers)
		{
			System.out.print(""+num+" ");
			dupArrayNumbers[i++] = num;
		}
		
		
		Arrays.sort(arrayNumbers);
		System.out.println("\nAfter Sorting :: ");
		for(int num: arrayNumbers)
		{
			System.out.print(""+num+" ");
		}
		
		System.out.println("\nCustom sort");
		for(i = 0;i<dupArrayNumbers.length;i++)
		{
			for(int j = i+1;j<dupArrayNumbers.length;j++)
			{
				if(dupArrayNumbers[i]>dupArrayNumbers[j])
				{
					int temp = dupArrayNumbers[i];
					dupArrayNumbers[i] = dupArrayNumbers[j];
					dupArrayNumbers[j] = temp;
				}
			}
		}
		
		for(var num:dupArrayNumbers)
		{
			System.out.print(""+num+" ");
		}
		
	}

}
