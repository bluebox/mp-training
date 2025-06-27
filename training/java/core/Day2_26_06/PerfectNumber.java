package Day2_26_06;
import java.util.Scanner;
public class PerfectNumber {
	   public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter a number to check if it's perfect: ");
	        int number = scanner.nextInt();

	        if (isPerfectNumber(number)) {
	            System.out.println(number + " is a perfect number.");
	        } else {
	            System.out.println(number + " is not a perfect number.");
	        }

	        scanner.close();
	    }

	    public static boolean isPerfectNumber(int number) {
	        if (number < 1) {
	            return false;
	        }

	        int sum = 0;

	        for (int i = 1; i < number; i++) {
	            if (number % i == 0) {
	                sum += i;
	            }
	        }

	        return sum == number;
	    }
}
