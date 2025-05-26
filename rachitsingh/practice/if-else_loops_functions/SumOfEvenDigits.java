import java.util.Scanner;

public class SumOfEvenDigits {
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter any number: ");
		int number = input.nextInt();
		System.out.println("Sum of even digits of the number = " + getEvenDigitSum(number));
	}
	public static int getEvenDigitSum(int number)
	{
		if(number < 0)
		{
			return -1;
		}
		else
		{
			int evenSum = 0;
			while(number != 0)
			{
				int digit = number % 10;
				if(digit % 2 == 0)
				{
					evenSum += digit;
				}
				number /= 10;
			}
			return evenSum;
		}
	}
}
