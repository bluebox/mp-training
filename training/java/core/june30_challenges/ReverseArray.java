package june30_collections;

public class ReverseArray {
	
	public static int[] reverseArray(int[] arr) {
		for(int i=0;i<(arr.length/2);i++) {
			int temp=arr[i];
			arr[i]=arr[arr.length-i-1];
			arr[arr.length-i-1]=temp;
	}
	return arr;
}}
