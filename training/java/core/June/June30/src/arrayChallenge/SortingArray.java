package arrayChallenge;
import java.util.Random;
import java.util.Arrays;

public class SortingArray {
	public static void main(String[] args) {
		
		int[] array = getArray(6);
		
		int i=0;
		int j=array.length -1;
		
		System.out.println("Initial Array: ");
		printArray(array);
		Arrays.sort(array);
		
		while( i < j) {
			int temp = array[i];
			array[i]=array[j];
			array[j]=temp;
			i++;
			j--;
		}
		
		System.out.println("After Sorting Array: ");
		printArray(array);
	}
	
	public static int[] getArray(int length) {
		
		int[] newArray = new int[length];
		Random random = new Random();
		
		for(int i=0;i<length;i++) {
			newArray[i]=random.nextInt(50);
		}
		
		return newArray;
	}
	
	public static void printArray(int[] array) {
		for(int element : array) {
			System.out.print(element + " ");
		}
		System.out.println();
	}
	
}
