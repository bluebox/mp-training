
public class DigitSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		digitSum(125);

	}
	public static void digitSum(int num)
	{
		int sum=0;
		while(num!=0)
		{
			int rem=num%10;
			sum+=rem;
			num=num/10;
		}
		System.out.println(sum);
	}

}

