
public class Challenge12 {

	public static boolean isOdd(int number)
	{
		if(number%2!=0)
			return true;
		else
			return false;
	}
	
	public static int sumOdd(int start,int end)
	{
		if(start<0 || end <0 || end<start)
			return -1;
		int sum=0;
		for(int i=start;i<=end;i++)
		{
			if(isOdd(i))
			{
				sum+=i;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isOdd(5));
		System.out.println(sumOdd(20,50));
		System.out.println(sumOdd(40,10));
		

	}

}
