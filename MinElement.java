import java.util.Scanner;
import java.util.Arrays;
public class MinElement {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] arr=readIntegers(n);
		for(int ele:arr)
			System.out.print(ele+" ");
		int mini=findMinimum(arr);
		System.out.println("minimum="+mini);

	}
	public static int[] readIntegers(int n) {
		Scanner sc=new Scanner(System.in);
		int[] result=new int[n];
		for(int i=0;i<n;i++) {
			result[i]=sc.nextInt();
		}
		return result;
	}
	public static int findMinimum(int[] arr) {
		int min=arr[0];
		for(int i=0;i<arr.length;i++) {
			if(min>arr[i])
				min=arr[i];
		}
		return min;
	}

}
