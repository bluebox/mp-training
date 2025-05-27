package examples;
import java.util.Scanner;

public class MinMaxChallenge {
	
	public static void main(String[] args) {
		String s = "";
		Scanner sc = new Scanner(System.in);
		int min,max;
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		do {
			System.out.println("Enter 'Q' to quit");
			System.out.println("Enter a number :: ");
			s = sc.nextLine();
			if(s.toLowerCase().charAt(0) == 'q')
			{
				break;
			}
			try
			{
				int number = Integer.parseInt(s);
				min = Math.min(min, number);
				max = Math.max(max, number);
				
			}catch(NumberFormatException nfe)
			{
				System.out.println("Entered is not a integer");
			}
		}while(true);
		
		System.out.println("Minimum is :: "+min);
		System.out.println("Maximum is :: "+max);
	}

}
