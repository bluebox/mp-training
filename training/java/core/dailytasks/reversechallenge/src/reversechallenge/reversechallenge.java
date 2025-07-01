package reversechallenge;

import java.util.Arrays;
import java.util.Scanner;

public class reversechallenge {

    // Method to reverse an integer array
    public static void reverse(int[] array) {
        int maxIndex = array.length - 1;
        int halfLength = array.length / 2;
        for (int i = 0; i < halfLength; i++) {
            int temp = array[i];
            array[i] = array[maxIndex - i];
            array[maxIndex - i] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int size = scanner.nextInt();

        int[] myArray = new int[size];

        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            myArray[i] = scanner.nextInt();
        }

        System.out.println("Original array: " + Arrays.toString(myArray));

        reverse(myArray); // Call the reverse method

        System.out.println("Reversed array: " + Arrays.toString(myArray));

        scanner.close();
    }
}