
public class DigitChecker {
	public static boolean hasShareDigit(int n1,int n2) {
		if(n1<10||n1>99||n2<10||n2>99) {
			return false;
		}
		int n1FirstDigit=n1/10;
		int n1SecondDigit=n1%10;
		
		int n2FirstDigit=n2/10;
		int n2SecondDigit=n2%10;
		return (n1FirstDigit==n2FirstDigit) || (n1FirstDigit==n2SecondDigit) ||(n1SecondDigit==n2FirstDigit)||(n1SecondDigit ==n2SecondDigit);
		
	}
}
