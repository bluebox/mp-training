package Day2_26_06;

public class DigitSum {
	public static void main(String args[]) {
		System.out.println(sumDigit(15));
		System.out.println(sumDigit(95));
		System.out.println(sumDigit(-58));
		System.out.println(sumDigit(1000));

	}
	public static int sumDigit(int num) {
		if(num<1) {
			return num==0?0:-1;
		}
		int sum=0;
		while(num>0) {
			sum+=num%10;
			num/=10;
		}
		return sum;
		
	}
}
