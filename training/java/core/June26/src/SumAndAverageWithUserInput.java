import java.util.*;

public class SumAndAverageWithUserInput {
	
	public static void main(String[] args) {
		
		inputThenPrintSumAndAverage();
	}
	public static void inputThenPrintSumAndAverage() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int sum =0;
		int count = 0;
		
		while(true) {
			System.out.print("Enter number" );
			String number=sc.nextLine();
			try {
				
				sum+=Integer.parseInt(number);
				count++;
				
			}catch(NumberFormatException e) {
				
				System.out.println("Invalid number");
				break;
				
			}
		}
		if (count > 0) {
            double average = (double) sum / count;
            System.out.println("Sum = " + sum + " AVG = " + Math.round(average));
        } else {
            System.out.println("No valid numbers entered.");
        }
	}
}
