package streams;
import java.util.Scanner;
public class SumOfDigitsWithoutRecursionLoop {

	public static void main(String[] args) {
		
		Scanner input=new Scanner(System.in);
		
		System.out.println("enter the number for sum =");
		int number_for_Sum=input.nextInt();
		int sum;
		
		if(number_for_Sum%9!=0) {
			sum=number_for_Sum%9;
		}
		else {
			sum=9;
		}
	
		System.out.println("Sum of digits until it gives single digit = "+sum);
		input.close();
		
	}
}
