class SumOfDgts
{
	public static int cmp(int n)
	{
		if(n<0)
		{
			return -1;
		}
		if(n>=0 && n<=9)
		{
			return n;
		}
		int sum=0;
		while(n>0)
		{
			int rem=n%10;
			sum+=rem;
			n=n/10;
			
		}
		return sum;
	}
	public static void main (String[] args)
	{
	  int n=345;
	  int sumofdgts=cmp(n);
	  System.out.println("sum of digits: " +sumofdgts);
	}
}