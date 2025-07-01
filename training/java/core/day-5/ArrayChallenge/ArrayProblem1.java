package ArrayChallenge;

import java.util.Arrays;

public class ArrayProblem1 {
public static void main(String []args)
{
	int[] arr1=new int[5];
	arr1[0]=3;
	arr1[1]=9;
	arr1[2]=67;
	arr1[3]=76;
	arr1[4]=4;
	System.out.print("Before sorting array:");
	for(int i=0;i<arr1.length;i++)
	{
	  System.out.print(arr1[i]+" ");
	}
	System.out.println();
	System.out.print("After sorting Array: ");
	Arrays.sort(arr1);
	for(int i=0;i<arr1.length;i++)
	{
		System.out.print(arr1[i]+" ");
	}
	
	
	
}
}
