package myjavaprograms;
import java.util.Scanner;
import java.util.Arrays;

public class TwoSort {
		public static void main(String args[]) {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("enter size :");
			int n = sc.nextInt();
			
			int arr[] = new int[n];
			
			for(int i=0; i<n; i++) {
				arr[i] = sc.nextInt();
			}
			
			System.out.println(Arrays.toString(arr));
			
			
			
			
		}
}
