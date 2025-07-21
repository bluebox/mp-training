public class LastDigitChecker {

	public static void main(String[] args) {
		System.out.println(hasSameLastDigit(41,22,71));
		System.out.println(hasSameLastDigit(23,32,42));
		System.out.println(hasSameLastDigit(9,99,999));
	}
	public static boolean hasSameLastDigit(int a,int b,int c) {
		if (!isInRange(a)||!isInRange(b)||!isInRange(c)) 
			return false;
		a=a%10;
		b=b%10;
		c=c%10;
		return a==b || a==c ||b==c;
	}
	public static boolean isInRange(int number) {
		return 10<=number && number<=1000;
	}
}