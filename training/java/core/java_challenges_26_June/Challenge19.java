
public class Challenge19 {

	public static boolean isPerfect(int number)
	{
		int sum=0;
		for(int i=1;i<=number/2;i++)
		{
			
			if(number%i==0)
			{
				sum+=i;
			}
		}
		if(sum==number)
		return true;
		else
			return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPerfect(28));
		

	}

}
