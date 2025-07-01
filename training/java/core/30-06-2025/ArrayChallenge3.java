//Predefined Array Methods

import java.util.Arrays;

public class ArrayChallenge3 {

	
	public static void main(String args[])
	{
		int arr1[]= {1,2,3,111,6};
		int arr2[][]= {{1,2,3,4,5,6}};
		System.out.println("asList Methos: "+Arrays.asList(arr1));
		System.out.println("BinarySearch Method: "+Arrays.binarySearch(arr1,3));
		System.out.println("Copyof Method:"+Arrays.toString(Arrays.copyOf(arr1, 10)));
		System.out.println("Deep String Method: "+ Arrays.deepToString(arr2));
//		Arrays.fill(arr1,3);
//		System.out.println("Fill method:" + Arrays.toString(arr1));
		Arrays.sort(arr1);
		System.out.println("Sorted array :"+Arrays.toString(arr1));
		
		
		
		
		
	}
}
