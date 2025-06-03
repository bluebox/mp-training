package collections;
import java.util.Scanner;
public class ReverseArray {
	static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) {
		int [] arr=takeInput();
		int miniNum=Integer.MAX_VALUE;
		System.out.println("the array before reverse :");
		for(int ele:arr)
		{
			System.out.println(ele);
		}
		
		arr=reverse(arr);
		System.out.println("the array after reverse :");
		for(int ele:arr)
		{
			System.out.println(ele);
		}
	}

	public static int[] reverse(int [] arr)
	{
		int temp,len=arr.length;
		
		for(int i=0;i<len/2;i++)
		{
			temp=arr[i];
			arr[i]=arr[len-1-i];
			arr[len-1-i]=temp;
		
		}
		return arr;
	}
	public static int[] takeInput()
	{
		System.out.println("enter the size of the array:");
		int n= sc.nextInt();
		int temp,ind=0;
		int [] arr = new int[n];
		while(n>0)
		{
			System.out.println("enter the ele #"+ (ind+1));
			temp=sc.nextInt();
			arr[ind++]=temp;
			n--;
			
		}
		return arr;
	}

}

