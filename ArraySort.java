import java.util.*;
public class ArraySort {

	public static void main(String[] args) {
		Random ran=new Random();
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] array=new int[n];
		for(int i=0;i<n;i++) {
			array[i]=ran.nextInt();
		}
		System.out.println("Array before Sorting:");
		for(int ele:array)
			System.out.print(ele+" ");
		System.out.println();
		Arrays.sort(array);
		System.out.println("Array after Sorting:");
		for(int i=n-1;i>=0;i--) {
			System.out.print(array[i]+" ");
		}
		

	}

}
