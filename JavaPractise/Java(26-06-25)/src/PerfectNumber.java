
public class PerfectNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPerfectNumber(5));
	}
	public static boolean isPerfectNumber(int n)
	{
		int sum=0;
		if(n<1)
			return false;
		for(int i=1;i<n;i++)
		{
			if(n%i==0)
			{
				sum+=i;
			}
		}
		if(sum==n)
			return true;
		else
			return false;
	}
}

