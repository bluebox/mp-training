package collections;

import java.util.Scanner;

public class Array {
	static Scanner sc= new Scanner(System.in);

	public static void main(String[] args) {
		
		int [] arr=takeInput();
		int miniNum=Integer.MAX_VALUE;
		for(int ele:arr)
		{
			
			miniNum= miniNum > ele?ele:miniNum;
		}
		System.out.println("the minimum number is :"+miniNum);
		

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
