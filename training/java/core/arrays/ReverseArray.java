package arraypractice;
import java.util.Arrays;
public class ReverseArray {

	public static void main(String[] args) {
		int[] array= {10,12,100,5,4,8};
		int[] arrayReversed= reverseArray(array);
		
		System.out.println("Original array is: "+Arrays.toString(array));
		System.out.println("Reversed array is: "+Arrays.toString(arrayReversed));
		

	}
	public static int[] reverseArray(int[] array) {
		int[] reverse= new int[array.length];
		for(int i=0;i<array.length;i++) {
			reverse[i]=array[array.length-i-1];
		}
		return reverse;
	}

}
