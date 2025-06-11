
public class DigitSum {
	public static void main(String[] args) {
		System.out.println(digitSum(456778));
		System.out.println(digitSum(-56));
		System.out.println(digitSum(1024));
	}
	public static int digitSum(int n)
	{
		if(n<0)
		{
			return -1;
		}
		int sum=0;
		while(n>0)
		{
			sum+=n%10;
			n/=10;
		}
		return sum;
	}

}
