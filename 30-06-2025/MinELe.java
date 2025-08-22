import java.util.Arrays;
import java.util.Scanner;

public class MinELe  {
    public static void main(String[] args) {
        int[] array = readIntegers();
        System.out.println("Array: " + Arrays.toString(array));
        int min = findMin(array);
        System.out.println("Minimum element: " + min);
    }

    public static int[] readIntegers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a comma delimited list of numbers: ");
        String input = scanner.nextLine();
        String[] numbers = input.split(",");
        int[] array = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            array[i] = Integer.parseInt(numbers[i].trim());
        }
        return array;
    }

    public static int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
}