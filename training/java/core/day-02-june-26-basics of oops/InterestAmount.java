package day2;

public class InterestAmount {

	public static void main(String[] args) {
		calculateInterest(100);
		calculateInterest(234);
	}
	public static void calculateInterest(double principal) {
		for(double rate=7.5;rate<10.0;rate+=0.25) {
			double interest=principal*(rate/100);
			System.out.println("interest for amount "+principal+
					" and rate "+rate+" is "+interest);
		}
	}
}
