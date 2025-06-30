import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of array:");
		int size=sc.nextInt();
		Integer[] arr =new Integer[size];
		
		for(int i=0;i<size;i++) {
			System.out.println("Eneter the "+(i+1)+" Value :");
			arr[i]=sc.nextInt();
		}
		System.out.println("The Original Array is :"+ Arrays.toString(arr));
		find_Rev(arr,size);

	}
	public static void find_Rev(Integer[] arr, int size) {
		int start=0, end=size-1;
		while(start<end) {
			int temp=arr[start];
			arr[start]=arr[end];
			arr[end]=temp;
			start++;
			end--;
		}
		System.out.println("The Reverse Order is  :"+Arrays.toString(arr));
	}

}
