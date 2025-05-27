package samplecodes;
import java.util.*;
public class MinimumElementChallenge {
	public static void main(String[] args) {
		int arr[]=new int[5];
		readInput(arr);
		System.out.println("Minimum Element is "+ findMin(arr));
	}
	public static void readInput(int arr[]) {
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<arr.length;i++) {
			arr[i]=sc.nextInt();
		}
		sc.close();
	}
	public static int findMin(int arr[]) {
		int min=Integer.MAX_VALUE;
		for(int x:arr) {
			min=Math.min(min, x);
		}
		return min;
	}
}
