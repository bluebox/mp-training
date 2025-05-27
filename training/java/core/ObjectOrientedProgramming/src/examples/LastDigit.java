package examples;

public class LastDigit {
	public static boolean hasSameLastDigit(int number1, int number2, int number3)
	{
		if (number1>1000 && number1<10)
		{
			return false;
		}else if (number2>1000 && number2<10)
		{
			return false;
		}else if (number3>1000 && number3<10)
		{
			return false;
		}else
		{
			return number1%10 == number2%10?true:number1%10 == number3%10?true:number2%10==number3%10?true:false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(hasSameLastDigit(11,22,34));
		System.out.println(hasSameLastDigit(10,86,30));
		System.out.println(hasSameLastDigit(42,72,1002));
		System.out.println(hasSameLastDigit(101,-907656,-101));
	}
}
