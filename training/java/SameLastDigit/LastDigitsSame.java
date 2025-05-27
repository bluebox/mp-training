package SameLastDigit;

public class LastDigitsSame {

	public static void main(String[] args) {
		int valueOne=4,valueTwo=35,valueThree=45;
		boolean flag= hasSameLastDigit(valueOne,valueTwo,valueThree);
		System.out.println(flag);
	}
	public static boolean hasSameLastDigit(int valueOne,int valueTwo, int valueThree)
	{
		if(!isValid(valueOne) || !isValid(valueTwo) || !isValid(valueThree))
		{
			return false;
		}
		return (valueOne % 10 == valueTwo %10 )|| ((valueTwo % 10) == (valueThree % 10)) || ((valueOne % 10 ) == (valueThree % 10));
	}
	public static boolean isValid(int value)
	{
		return (value >=10 && value <=1000);
	}

}
