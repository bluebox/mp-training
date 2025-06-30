import java.util.Arrays;

public class ReverseArray {

	public static void main(String[] args) {
	
		int[] arr= {1,2,3,4,5,6,7,8,9};
		
		
		System.out.println(arr.length);
		System.out.println(Arrays.toString(arr));
		
		int n=arr.length-1;
		
		for(int i=0;i<=arr.length/2;i++) {
			int t=arr[i];
			arr[i]=arr[n];
			arr[n]=t;
			n--;
		}
		System.out.println(Arrays.toString(arr));
	}

}
