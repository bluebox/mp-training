package learn;

public class EvenDigitSum {
	
	public static void main(String[] args) {
		int num = 1234567890;
		int result = getEvenDigitSum(num);
		if(result == -1) {
			System.out.println("invalid");
		}
		else {
			System.out.print("even digit sum of " + num + " is " + getEvenDigitSum(num));
		}
	}
	
	public static int getEvenDigitSum(int number) {
		int sum = 0;
		if(number < 0) {
			return -1;
		}
		while(number != 0) {
			
			int currentLastDigit = number % 10;
			
			if(currentLastDigit % 2 == 0) {
				sum += currentLastDigit;
			}
			number /= 10;
		}
		return sum;
	}
}
