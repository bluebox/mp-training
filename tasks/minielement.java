
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class minielement {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter number");
		int n=sc.nextInt();
		int[] a=new int[n];
		System.out.println("enter numbers:");
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
		}
		ArrayList<Integer> a1 = readIntegers(a);
		int min = findmin(a1);
		System.out.println("Minimum element: " + min);
		
	}
	public static ArrayList<Integer> readIntegers(int[] l1) {
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 0; i < l1.length; i++) {
			a.add(l1[i]);
		}
		return a;
	}
	public static int findmin(ArrayList<Integer> a){
		return Collections.min(a);
	}

}
