package Day2;

public class DigitSum {

	public static void main(String[] args) {
		int number=-191;
		int sumOfDigits=sumDigits(number);
		System.out.println("the sum of digits of given "+ number + " is "+sumOfDigits);

	}
	public static int sumDigits(int number) {
		int sum=0;
		while(number<0) {
			System.out.println("invalid number");
		}
		while(number>0) {
			int digit=number%10;
			sum+=digit;
			number /= 10;
		}
		return sum;
		
	}

}
