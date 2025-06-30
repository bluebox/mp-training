import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the length");
		int l=sc.nextInt();
		int arr[]=readIntegers(l);
		int arr1[]=reverseArray(arr);
		System.out.println(Arrays.toString(arr1));

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
	public static int[] reverseArray(int[] arr)
	{
		int p1=0;
		int p2=arr.length-1;
		while(p1<=p2)
		{
			int temp=arr[p1];
			arr[p1]=arr[p2];
			arr[p2]=temp;
			p1++;
			p2--;
		}
		return arr;
		
	}

}
