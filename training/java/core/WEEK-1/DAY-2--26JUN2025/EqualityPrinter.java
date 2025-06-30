public class EqualityPrinter {
    public static void printEqual(int a, int b, int c) {
        if (a < 0 || b < 0 || c < 0) {
            System.out.println("Invalid Value");
        } else if (a == b && b == c) {
            System.out.println("All numbers are equal");
        } else if (a != b && a != c && b != c) {
            System.out.println("All numbers are different");
        } else {
            System.out.println("Neither all are equal or different");
        }
    }

    public static void main(String[] args) {
        // Test case 1
        printEqual(1, 1, 1);   // Output: All numbers are equal

        // Test case 2
        printEqual(-1, -1, -1); // Output: Invalid Value

        // Test case 3
        printEqual(1, 2, 3);   // Output: All numbers are different

        // Test case 4
        printEqual(1, 1, 2);   // Output: Neither all are equal or different
    }
}
