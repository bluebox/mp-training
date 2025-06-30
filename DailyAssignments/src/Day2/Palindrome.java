package Day2;

public class Palindrome {

	public static void main(String[] args) {
		int number=101;
		int temp=number;
		int reverse=0;
		while(number>0) {
			int lastDigit=number%10;
			reverse=reverse*10+lastDigit;
			number/=10;
		}
		if(temp==reverse) {
			System.out.println("given "+ temp+" is palindrome");
		}else {
			System.out.println("give "+temp +" is not a palindrome");
		}

	}

}
