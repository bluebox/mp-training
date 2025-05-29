package javaPrograms;
import java.util.Arrays;
import java.util.Random;
public class RandomArray {
	
	public static void getRandomArray(int[] ar) {
		Random r=new Random();
		for(int i=0;i<ar.length;i++)
		{
			ar[i]=r.nextInt(100);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ar= new int[10];
		getRandomArray(ar);
		System.out.println("Before sorting :"+Arrays.toString(ar));
		Arrays.sort(ar);
		System.out.println("After sorting :"+Arrays.toString(ar));

		
	}

}
