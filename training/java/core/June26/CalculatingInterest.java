package June26;

public class CalculatingInterest {
	public static void main (String[] args) {
		double amount = 100;
		for(double i = 7.5; i <= 10; i += 0.25) {
			System.out.println("100 at " + i + "% interest = " + CalculateInterest(amount, i));
		}
	}
	public static double CalculateInterest (double amount, double interestRate) {
		return (amount*(interestRate/100));
	}
}
