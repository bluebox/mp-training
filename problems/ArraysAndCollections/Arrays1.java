package ArraysAndCollections;

import java.util.*;

public class Arrays1 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		// Challenge 1
		Random random=new Random();
		System.out.println("Enter the length of random array");
		int length=Integer.parseInt(sc.nextLine());
		int arr[]=new int[length];
		for(int i=0;i<length;i++) {
			arr[i]=random.nextInt(100);
		}
		
		System.out.println("Random array before Sorting  :"+Arrays.toString(arr));
		
		
		Arrays.sort(arr);
		
		System.out.println("Random array after Sorting  :"+Arrays.toString(arr));
		
		// Challenge 2
		String number_arr=sc.nextLine();
		String arr1[]=number_arr.split(",");
		int arr2[]=new int[arr1.length];
		int min=Integer.MAX_VALUE;
		for(int i=0;i<arr1.length;i++) {
			arr2[i]=Integer.parseInt(arr1[i]);
			if(arr2[i]<min) {
				min=arr2[i];
			}
		}
		
		System.out.println("Array after the operation  :"+Arrays.toString(arr2));
		System.out.println("Minimum value in the Array is :"+min);
		
		// challenge 3
		System.out.println("Enter the length of the array");
		int length_1=Integer.parseInt(sc.nextLine());
		int []arr_3=new int[length_1];
		for(int i=0;i<length_1;i++) {
			arr_3[i]=Integer.parseInt(sc.nextLine());
		}
		
		System.out.println("Array before the reverse operation  :"+Arrays.toString(arr_3));
		reverse_arr(arr_3);
		System.out.println("Array before the reverse operation  :"+Arrays.toString(arr_3));
	}
	
	public static void reverse_arr(int [] arr) {
		int i=0;
		int j=arr.length;
		while(i<=j) {
			int temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
		}
	}
	

}
