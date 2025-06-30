package day4;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumElement {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter number delimetered with comma : ");
		String s=sc.nextLine();
		
		int[] arr=new int[s.length()];
		arr=readIntegers(s);
		System.out.println(Arrays.toString(arr));
		Minimum(arr);
	}
	
	public static int[] readIntegers(String str) {
		
		String[] ans=str.split(",");
		int[] num=new int[ans.length];
		for(int i=0;i<ans.length;i++) {
			num[i]=Integer.parseInt(ans[i]);
		}
		return num;
	}
	
	public static void Minimum(int[] arr) {
		int min=Integer.MAX_VALUE;
		for(int n :arr) {
			if(n<min) {
				min=n;
			}
		}
		System.out.println("Minimum element : "+min);
	}
}
