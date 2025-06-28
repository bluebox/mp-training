import java.util.Scanner;

public class StarPattern {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
        System.out.println("Enter a Value");

		int number=sc.nextInt();
		if (number < 5) {
            System.out.println("Invalid Value");
            return;
        }

        for (int row = 1; row <= number; row++) {
            for (int col = 1; col <= number; col++) {
                if (row == 1 || row == number ||           
                    col == 1 || col == number ||           
                    row == col ||                          
                    col == (number - row + 1)) {           
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
