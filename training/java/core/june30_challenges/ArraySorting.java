package june30_collections;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
public class ArraySorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of elements in Array:");
		int larray=sc.nextInt();
				
		int[] arr=new int[larray];
		
		Random rn=new Random();
		
		for(int i=0;i<larray;i++) {
			arr[i]=rn.nextInt(100);
		}
		System.out.println("Array before Sorting : "+Arrays.toString(arr));
		Arrays.sort(arr);

	        for (int i = 0; i < arr.length / 2; i++) {
	            int temp = arr[i];
	            arr[i] = arr[arr.length - i - 1];
	            arr[arr.length - i - 1] = temp;
	        }
		
		System.out.println("Array after Soring in descending Order : "+Arrays.toString(arr));

	}

}
