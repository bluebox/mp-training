package Day2;

public class MiniChallenge {

	public static void main(String[] args) {
		double amount=500.00;
		double interest=2.00;
		for(double i=7.5;i<=10;i+=0.25) {
		double interestAmount=calculateInterest(amount,interest);
		System.out.println(i+" the interest amount is "+ interestAmount);
		}

	}

	public static double calculateInterest(double amount, double interest) {
		
	
		return (amount*interest/100);
	}

}
