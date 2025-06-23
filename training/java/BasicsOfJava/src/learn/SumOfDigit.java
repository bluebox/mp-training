package learn;

public class SumOfDigit {

	public static void main(String[] args) {
		int digitSum=sumDigits(247);
		System.out.println(digitSum);
	}
	
	public static int sumDigits(int num) {
		int currentSum=0;
		while(num!=0) {
			currentSum+=num%10;
			num/=10;
		}
		return currentSum;
	}

}
