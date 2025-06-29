
public class Challeneg17 {
	public static boolean isPrime(int number)
	{
		for(int i=2;i<number/2;i++)
		{
			if(number%i==0)
				return false;
		}
		return true;
	}
	
	public static int getLargestPrime(int n)
	{
		int ans=-1;
		for(int i=2;i<n;i++)
		{
			if(isPrime(i) && (n%i)==0)
			{
				ans=Math.max(ans, i);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Largest Prime Number is "+getLargestPrime(217));
		

	}

}
