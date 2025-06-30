import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RandomNum {

	public static void main(String[] args) {
		
		Integer[] arr=new Integer[10];
		
		Random r=new Random();
		
		for(int i=0;i<10;i++) {
			arr[i]=r.nextInt(100);
		}
		System.out.println(Arrays.toString(arr));
		
		Arrays.sort(arr,Collections.reverseOrder());
		System.out.println(Arrays.toString(arr));
	}

}
