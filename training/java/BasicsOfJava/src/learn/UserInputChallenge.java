package learn;
import java.util.Scanner;
public class UserInputChallenge {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int sum = 0;
		int i = 1;
		do {
			try {
				System.out.println("Enter number #"+ i + " ");
				String input = scanner.nextLine();
				int num = Integer.parseInt(input);
				sum += num;
				i+=1;
			}
			catch(NumberFormatException e) {
				System.out.println("invalid");
			}
		}while(i<=5);
		System.out.println("sum of the 5 numbers is" + sum);
		
	}
}
