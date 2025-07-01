
    import java.util.Scanner;

public class MinFinder {
    
    

    private static int readInteger() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements:");
        int count = scanner.nextInt();
        return count;
    }

    private static int[] readElements(int count) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[count];

        System.out.println("Enter " + count + " elements:");
        for (int i = 0; i < count; i++) {
            array[i] = scanner.nextInt();
        }

        return array;
    }

    private static int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
}


