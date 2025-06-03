package Arrays;
import java.util.*;

public class ReversArray {
	
	public static void getReversedArray(int[] ar,int n){
		for(int i=n-1;i>=0;i--) {
			System.out.print(ar[i]+" ");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the no of elements : ");
		int n=sc.nextInt();
		int[] ar=new int[n];
		System.out.print("Enter the elements : ");
		for(int i=0;i<n;i++) {
			ar[i]=sc.nextInt();
		}
		System.out.println("Array is : "+Arrays.toString(ar));
		System.out.println("Reversed Array is : ");
		getReversedArray(ar,n);
	}

}
