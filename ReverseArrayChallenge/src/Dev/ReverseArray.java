package Dev;
	
import java.util.Arrays;

public class ReverseArray {

    public static void reverse(int[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;

            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {4,2,1,4,1,3};

        System.out.println("Original array: " + Arrays.toString(numbers));
        reverse(numbers);
        System.out.println("Reversed array: " + Arrays.toString(numbers));
    }
}
