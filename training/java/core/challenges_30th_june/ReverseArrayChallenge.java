package challenges_30th_june;

import java.util.Arrays;

public class ReverseArrayChallenge {
	
	public static void reverse(int arr[]) {
		int i=0;
		int j=arr.length-1;
		while(i<j) {
			int temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			i++;
			j--;
		}
	}
	public static void main(String args[]) {
		int arr[]= {1,2,3,4,5};
		System.out.println(Arrays.toString(arr));
		reverse(arr);
		System.out.println(Arrays.toString(arr));
	}
	
}
