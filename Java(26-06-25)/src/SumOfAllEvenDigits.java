
public class SumOfAllEvenDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getEvendigitSum(123456789));

	}
	public static boolean isEven(int num)
	{
		if(num%2==0)
		{
			return true;
		}
		else
			return false;
	}
	public static int getEvendigitSum(int num)
	{
		int sum=0;
		if(num<0)
			return -1;
		while(num!=0)
		{
			int rem=num%10;
			if(isEven(rem))
				sum+=rem;
			num=num/10;
		}
		return sum;
	}

}

