import java.util.*;
public class ArrayReverseChallenge {

	public void reverse(int arr[],int n)
	{
		System.out.println("Before Reversing the array :"+Arrays.toString(arr));
		
		int i=0,j=n-1;
		while(i<j)
		{
			int temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			i++;
			j--;
		}
		System.out.println("After reversing the array : "+Arrays.toString(arr));
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		int n=s.nextInt();
		System.out.println("Enter the array elements: ");
		int numbers[]=new int[n];
		for(int i=0;i<n;i++)
		{
			numbers[i]=s.nextInt();
		}
		ArrayReverseChallenge a=new ArrayReverseChallenge();
		a.reverse(numbers,n);
	}

}
