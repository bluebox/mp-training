class OddSum
{
	public static boolean isOdd(int val)
	{
		if(val<0 || val%2==0)
		{
			return false;
		}
		return true;
			
	}
	public static int sumOdd(int st,int end)
	{
		if(end<=st || st<0 || end<0)
		{
			return -1;
		}
		int sum=0;
		for(int i=st;i<=end;i++)
		{
			if(isOdd(i))
			{
				sum+=i;
			}
		}
		return sum;
	}
	public static void main (String[] args)
	{
		System.out.println("Sum is :"+sumOdd(1,100));
	}
}