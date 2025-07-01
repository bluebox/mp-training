import java.util.Arrays;
import java.util.Scanner;

public class revch {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter n:");
		int n=sc.nextInt();
		int[] a=new int[n];
		System.out.println("enter numbers");
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
		}
		for(int i = 0; i < n / 2; i++) {
			int t = a[i];
			a[i] = a[n - i - 1];
			a[n - i - 1] = t;
		}
		System.out.println(Arrays.toString(a));
		
	}

}
