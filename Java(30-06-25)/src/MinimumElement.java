import java.util.*;
public class MinimumElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the length of an array");
		int len=sc.nextInt();
		int arr1[]=readIntegers(len);
		int min=findMin(arr1);
		System.out.println("minimum element is "+min);
	}
	public static int[] readIntegers(int length)
	{
		Scanner sc=new Scanner(System.in);
		int arr[]=new int[length];
		System.out.println("Enter array elements");
		for(int i=0;i<length;i++)
		{
			arr[i]=sc.nextInt();
		}
		return arr;
	}
	public static int findMin(int arr[])
	{
		int min=Integer.MAX_VALUE;
		for(int i=0;i<arr.length;i++)
		{
			min=Math.min(min, arr[i]);
		}
		return min;
	}

}
