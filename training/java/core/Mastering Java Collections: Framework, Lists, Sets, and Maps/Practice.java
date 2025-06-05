import java.util.*;

public class Practice {
    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6));

        System.out.println("Original list: " + numbers);

        // Exploring essential methods and list operations
        System.out.println("\nEssential Methods and List Operations:");

        // Shuffle the list
        Collections.shuffle(numbers);
        System.out.println("Shuffled list: " + numbers);

        // Reverse the list
        Collections.reverse(numbers);
        System.out.println("Reversed list: " + numbers);

        // Sort the list
        Collections.sort(numbers);
        System.out.println("Sorted list: " + numbers);

        // Find the index of a sublist
        List<Integer> sublist = Arrays.asList(3, 7, 4);
        int index = Collections.indexOfSubList(numbers, sublist);
        System.out.println("Index of sublist " + sublist + ": " + index);

        // Mastering methods
        System.out.println("\nMastering Methods:");

        // Binary search
        int searchElement = 5;
        int index2 = Collections.binarySearch(numbers, searchElement);
        System.out.println("Index of " + searchElement + ": " + index2);

        // Frequency
        int frequency = Collections.frequency(numbers, 3);
        System.out.println("Frequency of 3: " + frequency);

        // Min and max
        int min = Collections.min(numbers);
        int max = Collections.max(numbers);
        System.out.println("Min: " + min + ", Max: " + max);

        // Rotate
        Collections.rotate(numbers, 3);
        System.out.println("Rotated list: " + numbers);
    }
}
