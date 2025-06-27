package Day2_26_06;

public class SharedDigit {
	public static void main(String args[]) {
		System.out.println(hasSharedDigit(15,25));
		System.out.println(hasSharedDigit(12,25));
		System.out.println(hasSharedDigit(16,25));

	}
	public static boolean hasSharedDigit(int num1,int num2) {
		if(num1>99 || num2 > 99 || num1<10 || num2 <10) {
			return false;
		}
		int n11=num1%10,n21=num2%10;
		int n12=num1/10,n22=num2/10;
		if(n11==n12 || n11==n22) {
			return true;
		}
		if(n21==n12 || n21== n22) {
			return true;
		}
		return false;
		
	}
}
