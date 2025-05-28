package SumOfDigitsInNumber;

public class SumOfDigits {

	public static void main(String[] args) {
		int number=2348;
		int sumOfDigit= digitSum(number);
		System.out.println("the sum of the digits in the number is: "+ sumOfDigit);
	}
	
	public static int digitSum(int number)
	{
		if( number < 0) return -1;
		int remainder = 0;
		int sum = 0;
		while(number > 0)
		{
			remainder=number%10;
			number/=10;
			sum+=remainder;
		}
		 return sum;
	}

}
