package Day2_26_06;

public class FirstAndLastDigitSum {
	public static void main(String args[]) {
		System.out.println(sumFirstLastDigit(159));
		System.out.println(sumFirstLastDigit(0));
		System.out.println(sumFirstLastDigit(168));
		System.out.println(sumFirstLastDigit(-15996845));
		System.out.println(sumFirstLastDigit(151));

	}
	public static int sumFirstLastDigit(int num) {
		if(num<0) {
			return -1;
		}
		int sum=0;
		sum+=num%10;
		while(num>=9) {
			num/=10;
		}
		sum+=num;
		return sum;
	}
}
