package Day2_26_06;

public class EvenDigitSum {
	public static void main(String args[]) {
		System.out.println(sumOfEvenDigits(1638745));
		System.out.println(sumOfEvenDigits(0));
		System.out.println(sumOfEvenDigits(1526418));

	}
	public static int sumOfEvenDigits(int num) {
		if(num<0) {
			return -1;
		}
		int sum=0;
		while(num>0) {
			int rem=num%10;
			if(rem%2==0) {
				sum+=rem;
			}
			num=num/10;
		}
		return sum;
	}
}
