package learn;
import java.util.Scanner;
public class InputCalculator {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int minimumNumber = 0, maximumNumber = 0,sum = 0,countOfNumbers = 0;
		try
		{
			do {
				System.out.println("Enter a number ");
				
				var input = scanner.nextLine();
				int number = Integer.parseInt(input);
				
				countOfNumbers += 1;
				
				if(countOfNumbers == 1) {
					minimumNumber = number;
					maximumNumber = number;
				}
				else {
					if(number < minimumNumber) {
						minimumNumber = number;
					}
					
					if(number > maximumNumber) {
						maximumNumber = number;
					}
				}
				sum += number;
			}while(true);
		}
		catch(NumberFormatException e)
		{
			System.out.println("minimum number is "+ minimumNumber);
			System.out.println("maximum number is "+ maximumNumber);
			System.out.println("sum number is "+ sum);
			if(countOfNumbers != 0) {
				System.out.println("Average of numbers is "+ sum/countOfNumbers);
			}
		}
	}
}
