public class DiagonalStar {

    public static void printSquareStar(int number) {
        if (number < 5) {
            System.out.println("Invalid Value");
            return;
        }

        for (int row = 1; row <= number; row++) {
            for (int col = 1; col <= number; col++) {
                if (row == 1 || row == number || col == 1 || col == number
                    || row == col || col == (number - row + 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println(); // Move to the next line
        }
    }

    public static void main(String[] args) {
        // Example 1
        printSquareStar(5);

        System.out.println(); // Space between examples

        // Example 2
        printSquareStar(8);

        System.out.println();

        // Invalid case
        printSquareStar(3);  // Should print "Invalid Value"
    }
}
