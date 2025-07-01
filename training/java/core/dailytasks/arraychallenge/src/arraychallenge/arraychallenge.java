
package arraychallenge;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class arraychallenge {

public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);


	        System.out.print("Enter the number of elements for the array: ");
	        int size = scanner.nextInt();

	
	        Integer[] array = new Integer[size];
	        Random random = new Random();
	        for (int i = 0; i < size; i++) {
	            array[i] = random.nextInt(100); 
	        }

	     
	        System.out.println("Array before sorting: " + Arrays.toString(array));

	       
	        Arrays.sort(array, Collections.reverseOrder());

	        System.out.println("Array after sorting (descending): " + Arrays.toString(array));

	       
	    }
	}