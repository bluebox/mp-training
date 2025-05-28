package Arrays;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseOrder {
	public static int[] getReverse(int[] arr) {
		int len=arr.length;
		int[] newArr=new int[len];
		for(int i=0;i<len;i++) {
			newArr[i]=arr[len-i-1];
		}
		return newArr;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println(" enter size:");	
		int len=sc.nextInt();
		int[] arr=new int[len];
		System.out.println("Enter elements:");
		
		for(int i=0; i< len;i++) {
			arr[i]=sc.nextInt();
			
		}
		Arrays.sort(arr);
		System.out.println("Reverse of  sorted array is " + Arrays.toString(getReverse(arr)));
		sc.close();
	}
}
