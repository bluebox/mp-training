package minimumOfArray;

import java.util.Scanner;

public class minOfArray {
	public static int[] readIntegers()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter no of elements to be taken :");
		int n=sc.nextInt();
		int[] arr=new int[n];
		System.out.println("Enter "+n+" Elements :");
		for(int i=0;i<n;i++)
		{
			arr[i]=sc.nextInt();
		}
		sc.close();
		return arr;
	
	}
	
	public static int findMin(int [] arr)
	{
		int min=arr[0];
		for(int i=1;i<arr.length;i++)
		{
			if(min>arr[i])
			{
				min=arr[i];
			}
		}
		return min;
	}
public static void main(String []args)
{
	int[] arr=readIntegers();
	System.out.println("minimum of given elements is : "+findMin(arr));

}

}
