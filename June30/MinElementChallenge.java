package June30;
import java.util.Scanner;

public class MinElementChallenge {
	public static void main(String[] args) {
		
		int[] arr=readIntegers();
		System.out.println("Minimum element : " + findMin(arr));
	}
	
	public static int[] readIntegers() {
		System.out.println("Enter array elements");
		Scanner sc=new Scanner(System.in);
		String[] strInput = sc.next().split(",");
		int[] arr=new int[strInput.length];
		for(int i=0;i<strInput.length;i++) {
			arr[i]=Integer.parseInt(strInput[i]);
		}
		sc.close();
		return arr;
	}
	
	public static int findMin(int[] arr) {
		int out=arr[0];
		for(int i=1;i<arr.length;i++) {
			out=Math.min(out,arr[i]);
		}
		return out;
	}
}
