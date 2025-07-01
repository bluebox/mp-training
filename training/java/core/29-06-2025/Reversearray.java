import java.util.Scanner;
import java.util.Arrays;

public class Reversearray {
    public static void main(String[] args) {
        int[] returnArray = readIntegers();
        System.out.println("Original array: " + Arrays.toString(returnArray));

        reverse(returnArray); // Variable name corrected
        System.out.println("Reversed array: " + Arrays.toString(returnArray));
    }

    private static int[] readIntegers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many integers:");
        int count = scanner.nextInt();
        int[] array = new int[count];

        System.out.println("Enter " + count + " numbers:");
        for (int i = 0; i < count; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    private static int findMin(int[] array) {
        int min = Integer.MAX_VALUE; // Corrected spelling
        for (int el : array) {
            if (el < min) {
                min = el;
            }
        }
        return min;
    }

    private static void reverse(int[] array) {
        int maxIndex = array.length - 1;
        int halfLength = array.length / 2;
        for (int i = 0; i < halfLength; i++) {
            int temp = array[i];
            array[i] = array[maxIndex - i];
            array[maxIndex - i] = temp;
        }
    }
}

