import java.util.*;
public class ArrayChallenge2 {
	
	public int[] readNumbers(int n)
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the Numbers into array: ");
		int numbers[]=new int[n];
		for(int i=0;i<n;i++)
		{
			numbers[i]=s.nextInt();
		}
		return numbers;
		
	}
	
	public int findMin(int arr[],int n)
	{
		int min=Integer.MAX_VALUE;
		for(int i=0;i<n;i++)
		{
			if(arr[i]<min)
				min=arr[i];
		}
		return min;
	}

	
	public static void main(String[] args) {
		
		Scanner s=new Scanner(System.in);
		System.out.println("ENter the size of the array :");
		int n=s.nextInt();
		ArrayChallenge2 a=new ArrayChallenge2();
		int numbers[]=a.readNumbers(n);
		Arrays.sort(numbers);
		System.out.println("Array Elements are :"+Arrays.toString(numbers));
		int ans=a.findMin(numbers, n);
		System.out.println("Min number in the array is:"+ans);
	}

}
