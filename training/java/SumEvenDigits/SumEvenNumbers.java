package SumEvenDigits;

public class SumEvenNumbers {

	public static void main(String[] args) {
		int number=987782345;
		int sum = sumOfEvenDigits(number);
		System.out.println("the sum of "+number+" is ="+ sum);
	}
	public static int sumOfEvenDigits(int number)
	{
		int sum=0,temp=0;
		while(number > 0)
		{
			temp=number%10;
			if(temp % 2 == 0)
			{
				sum+=temp;
			}
			number/=10;
		}
		
		return sum;
	}

}
