
public class LargestPrimeFactor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(largestPrimeFactor(45));
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
	public static int largestPrimeFactor(int num)
	{
		if(num<=0)
			return -1;
		int max=Integer.MIN_VALUE;
		for(int i=1;i<num;i++)
		{
			if(num%i==0&&isPrime(i))
			{
				max=Math.max(i,max);
			}
		}
		return max;
	}


}

