import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class Array_Challenge {

	public static void main(String[] args) {
		int size=10;
		Integer[] arr =new Integer[size];
		Random ran =new Random();
		for(int i=0;i<size;i++) {
			arr[i]=ran.nextInt(100);
		}
		System.out.println("The Original Array is :"+ Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println("The Ascending order Array is :"+ Arrays.toString(arr));
		Arrays.sort(arr,Collections.reverseOrder());
		System.out.println("The Descending order Array is :"+ Arrays.toString(arr));
		

	}

}
