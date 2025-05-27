package HasSharedDigit;

public class SharedDigit {

	public static void main(String[] args) {
		int valueOne = 1,valueTwo = 31;
		System.out.println(hasSharedDigit(valueOne,valueTwo));

	}
	public static boolean hasSharedDigit(int valueOne , int valueTwo)
	{
		if( isValid(valueOne) || isValid(valueTwo))
		{
			return false;
		}
		String str= Integer.toString(valueOne);
		int temp;
		while(valueTwo >0)
		{
			temp=valueTwo%10;
			if( str.contains(Integer.toString( temp)))
			{
				return true;
			}
			valueTwo/=10;
		}
		return false;
	}
	public static boolean isValid(int value)
	{
		return value <10 || value >99;
	}

}
