package loops;
import java.util.Scanner;

public class PrintUserInput {
	public static void main(String[] args) {
		int limit=5, sum =0 ;
		Scanner sc = new Scanner(System.in);
		int i=0;
		while( i < limit) {
			System.out.print("Enter number #"+ (i+1) + ":");
			String myVariable = sc.nextLine();
			if((myVariable.charAt(0)) >  47 && (myVariable.charAt(0))<58) {
				sum += (myVariable.charAt(0)) - 48;
				i++;
				}
			else
				System.out.println("Invalid number");
		}
		System.out.println("sum of given numbers is:"+sum);
	}
}
