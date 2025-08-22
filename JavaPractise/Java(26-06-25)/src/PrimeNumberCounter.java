
public class PrimeNumberCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count=0;
		for(int i=1;i<=15;i++)
		{
			if(isPrime(i))
			{
				System.out.println(i);
				count++;
			}
			if(count==3)
				break;
		}

	}
	public static boolean isPrime(int num)
	{
		if(num<=1)
		{
			return false;
		}
		if(num==2)
		{
			return true;
		}
		for(int i=2;i<=Math.sqrt(num);i++)
		{
			if(num%i==0)
			{
				return false;
			}
		}
		return true;
	}

}

