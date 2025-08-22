package Arrays;
import java.util.Arrays;
public class ArrayChallenge {
	public static void sortDescending(int[] array) {
	        for (int i = 0; i < array.length - 1; i++) {
	            for (int j = i + 1; j < array.length; j++) {
	                if (array[i] < array[j]) {
	                    int temp = array[i];
	                    array[i] = array[j];
	                    array[j] = temp;
	                }
	            }
	        }
}

	   public static void main(String[] args) {
		   int[] numbers = {50, 25, 80, 5, 15};
		   System.out.println("Original array: " + Arrays.toString(numbers));
		   sortDescending(numbers);
		   System.out.println("Sorted array (Descending): " + Arrays.toString(numbers));
	   }
}
