
public class SumOfOdd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sumOdd(100,1000));

	}
	public static boolean isOdd(int num)
	{
		if(num%2!=0)
		{
			return true;
		}
		else
			return false;
	}
	public static int sumOdd(int start,int end)
	{
		int sum=0;
		if(start<0||end<0)
		{
			return -1;
		}
		for(int i=start;i<=end;i++)
		{
			if(isOdd(i))
			{
				sum+=i;
			}
		}
		return sum;
	}

}

