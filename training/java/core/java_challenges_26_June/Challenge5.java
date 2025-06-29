
public class Challenge5 {

	public static int sumDigits(int num)
	{
		if(num<0)
		{
			System.out.println("Invalid Number");
			return -1;
		}
		int result = 0;
		
		while(num>0)
		{
			int remainder = num%10;
			result+=remainder;
			num = num /10;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sumDigits(125));
		System.out.println(sumDigits(5678389));
		System.out.println(sumDigits(-123));	

	}

}
