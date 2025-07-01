package tasks;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class arrch {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter number");
		Random r=new Random();
		int k=sc.nextInt();
		int[] a=new int[k];
		for(int i=0;i<k;i++) {
			a[i]=r.nextInt(100);
		}
		System.out.println("before sorting:");
		System.out.println(Arrays.toString(a));
		System.out.println("after sorting:");
		Integer[] aBoxed = Arrays.stream(a).boxed().toArray(Integer[]::new);
		Arrays.sort(aBoxed, Collections.reverseOrder());
		System.out.println(Arrays.toString(aBoxed));


		
		
		
	}

}
