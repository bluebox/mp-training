package examples;

public class SumOfEvenDigits {
	
	public static int sumOfEvens(int number)
	{
		int sum= 0;
		while(number >0)
		{
			sum += ((number%10)%2) == 0?number%10:0;
			number = number/10;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(sumOfEvens(12345));
	}
}
