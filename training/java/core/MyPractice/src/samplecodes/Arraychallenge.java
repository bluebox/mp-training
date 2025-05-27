package samplecodes;
import java.util.Arrays;
import java.util.Random;
public class Arraychallenge {
	public static void main(String[] args) {
		// Descending order 
		// use random class to generate random numbers
		int arr[]=new int[5];
		Random random=new Random();
		for(int i=0;i<arr.length;i++) {
			arr[i]=random.nextInt(100);
		}
		System.out.println("Before Sorting : "+Arrays.toString(arr));
		Arrays.sort(arr);
		int i=0;
		int j=arr.length-1;
		while(i<j)
		{
			int temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			i++;
			j--;
		}
		System.out.println("After Sorting : "+Arrays.toString(arr));
	}
}
