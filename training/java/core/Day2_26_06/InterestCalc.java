package Day2_26_06;

public class InterestCalc {
	public static void main(String args[]) {
		double amount=100;
		for(double i=7.5;i<=10;i+=0.25) {
			System.out.println("Interest for the amount "+amount+" at "+i+" interest rate is "+calculator(amount,i));
		}
	}
	public static double calculator(double amount,double rate) {
		return amount*(rate/100);
	}
}
