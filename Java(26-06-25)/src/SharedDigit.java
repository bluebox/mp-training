

public class SharedDigit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(hasSharedDigit(12,22));

	}
	public static boolean hasSharedDigit(int num1,int num2)
	{
		if(num1<10||num1>99||num2<10||num2>99)
			return false;
		int num1FD=num1/10;
		int num1SD=num1%10;
		int num2FD=num2/10;
		int num2SD=num2%10;
		if(num1FD==num2FD||num1FD==num2SD||num1SD==num2FD||num1SD==num2SD)
		{
			return true;
		}
		else
			return false;
		}
}

		
		