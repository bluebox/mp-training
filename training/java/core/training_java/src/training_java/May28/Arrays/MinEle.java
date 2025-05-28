package Arrays;

import java.util.Scanner;
public class MinEle {
	public static int findMin(int[] arr) {
		int min=arr[0];
		for(int ele:arr) {
			if(ele<min) {
				min=ele;
			}
		}
		return min;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println(" enter size:");	
		int len=sc.nextInt();
		int[] arr=new int[len];
		System.out.println("Enter elements:");
		
		for(int i=0; i< len;i++) {
			arr[i]=sc.nextInt();
			
		}
		System.out.println("Minimum element of array is " + findMin(arr));
		sc.close();
	}
	

}
