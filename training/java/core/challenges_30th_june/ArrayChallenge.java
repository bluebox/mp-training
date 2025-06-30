package challenges_30th_june;

import java.util.Arrays;
import java.util.Random;

public class ArrayChallenge {
	public static void main(String args[]) {
		int arr[]=new int[5];
		Random random=new Random();
		
		for(int i=0;i<arr.length;i++) {
			arr[i]=random.nextInt(50);
		}
		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
