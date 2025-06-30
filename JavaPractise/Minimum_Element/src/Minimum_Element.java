import java.util.Arrays;
import java.util.Scanner;

public class Minimum_Element {

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
		System.out.println("The Minimum element is :"+find_Min(arr,size));

	}
	public static int find_Min(Integer[] arr, int size) {
		int mini=Integer.MAX_VALUE;
		for(int i=0;i<size;i++) {
			if(arr[i]<mini) {
				mini=arr[i];
			}
		}
		return mini;
	}

}
