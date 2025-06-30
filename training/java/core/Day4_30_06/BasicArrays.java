package Day4_30_06;

import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class BasicArrays {
	public static void main(String args[]) {
		Random rand=new Random();
		Integer[] arr1=new Integer[10];
		for(int i=0;i<arr1.length;i++) {
			arr1[i]=rand.nextInt(100);
		}
		for(int e:arr1) {
			System.out.print(e+" ");
		}
		System.out.println();
		Arrays.sort(arr1,Collections.reverseOrder());
		for(int e:arr1) {
			System.out.print(e+" ");
		}
		System.out.println();
	}
}
