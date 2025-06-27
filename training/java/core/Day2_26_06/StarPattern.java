package Day2_26_06;

public class StarPattern {
	 public static void main(String[] args) {
	        printSquareStar(5);
	        System.out.println();
	        printSquareStar(8);
	        System.out.println();
	        printSquareStar(3);  // Should print "Invalid Value"
	    }

	    public static void printSquareStar(int number) {
	        if (number < 5) {
	            System.out.println("Invalid Value");
	            return;
	        }

	        for (int row = 0; row < number; row++) {
	            for (int col = 0; col < number; col++) {
	                if (row == 0 || row == number - 1 ||         // Top and bottom borders
	                    col == 0 || col == number - 1 ||         // Left and right borders
	                    row == col ||                            // Main diagonal
	                    col == number - row - 1) {               // Opposite diagonal
	                    System.out.print("*");
	                } else {
	                    System.out.print(" ");
	                }
	            }
	            System.out.println();  // Move to next row
	        }
	    }
}
