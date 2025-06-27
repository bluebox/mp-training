
public class PrimeNumberChallenge {
	
	public static boolean isPrime(int number)
	{
		for(int i=2;i<number;i++)
		{
			if(number%i==0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count=1;
		for(int i=2;i<=100;i++)
		{
			if(isPrime(i))
			{
				System.out.println(i+" is the "+count+"st prime number");
				count++;
				
			}
		}

	}

}
