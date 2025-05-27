package PerfectNumber;

public class IsPerfectNumber {

	public static void main(String[] args) {
		int number=6;
		System.out.println(isPerfectNumber(number));
	}
	public static boolean isPerfectNumber(int number)
	{
		if(number < 0)
			return false;
		int sum=0;
		for(int i=1;i<number;i++)
		{
			if(number % i == 0)
			{
				sum+=i;
			}
		}
		return number == sum;
	}

}
