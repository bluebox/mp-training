
public class SameLastDigit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(hasSameLastDigit(10,1000,200));
		System.out.println(isValid(1051));
		
	}
	public static boolean hasSameLastDigit(int n1,int n2,int n3)
	{
		if(isValid(n1)&&isValid(n2)&&isValid(n3))
		{
		int a=n1%10;
		int b=n2%10;
		int c=n3%10;
		if(a==b||b==c||c==a)
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		else
			return false;
		
	}
	public static boolean isValid(int num)
	{
		if(num>=10&&num<=1000)
			return true;
		else
			return false;
	}

}

