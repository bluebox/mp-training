package Factors;

public class LisitngFactors {

	public static void main(String[] args) {
		
		int number=-15;
		printFactors(number);
	}
	public static void printFactors(int number)
	{
		if(number < 1)
		{
			System.out.println("invalid Value ");
			return ;
		}
		for(int i=1;i<=number;i++)
		{
			if(number % i == 0)
			{
				System.out.println(i);
			}
		}
	}

}
