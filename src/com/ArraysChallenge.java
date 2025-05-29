import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ArraysChallenge {
	
	static Integer[] arr = new Integer[5];
	
	public static void main(String[] args) {
		initialiseArray();
		printArray();
		sortArray();
		reverse();
		printArray();
	}
	
	public static void reverse() {
		// asList binds the created list with the original array passed as an argument to it,
		// hence any changes to the created list on top of List hence created changes the original array
		List<Integer> l = Arrays.asList(arr);
		Collections.sort(l, Collections.reverseOrder());
	}
	
	public static void initialiseArray() {
		
		Random random = new Random();
		for(int i=0;i<5;i++) {
			arr[i] = random.nextInt(45);
		}
		
	}
	
	public static void printArray() {
		System.out.println(Arrays.toString(arr));
	}
	
	public static void sortArray() {
		Arrays.sort(arr);
	}
	
}
