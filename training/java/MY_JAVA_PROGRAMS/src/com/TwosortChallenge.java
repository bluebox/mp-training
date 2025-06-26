package com;
import java.util.Scanner;
import java.util.Arrays;

public class TwosortChallenge {
		public static void main(String args[]) {
			
			Scanner sc = new Scanner(System.in);
			
			//1 ,2 ,5 ,6 ,7
			
			//1, 2, 3, 4, 5, 6
			
			//System.out.println("enter size :");
			//int n = sc.nextInt();
			
			int arr[] = {1,2,5,6,7};
			
//			for(int i=0; i<n; i++) {
//				arr[i] = sc.nextInt();
//			}
			
			System.out.println(Arrays.toString(arr));
			
			Arrays.sort(arr);
			
			int n = arr.length;
			
			int ans[] = new int[n];
			
			int st = 0;
			
			if (n%2 == 0) {
				for(int i=0; i<(int)n/2; i++) {
					ans[st] = arr[n-i-1];
					st+=2;
					
				}
			}
			
			else {
				
				for(int i=0; i<=(int)n/2; i++) {
					ans[st] = arr[n-i-1];
					st+=2;
					
				}
				
			}
						
			
			st = 1;
			
			for(int i=0; i<(int)n/2; i++) {
				ans[st] = arr[i];
				st+=2;
			}
			
			System.out.println(Arrays.toString(ans));		
			
		}
}
