package MethodOverloading;

public class TaxCalculator {
	// if amount only given will calcilated by default rate
	public static double calculateTax(double amount) {
		double gstRate = 8.5d;
		return amount * gstRate;
	}

	// if tax percentage mentioned then this will be invoked
	public static double calculateTax(double amount, double taxPercent) {
		return amount * taxPercent;
	}

	// if specific state has varried tax rate and statecode is mentioned then this
	// will be invoked
	public static double calculateTax(double amount, String stateCode) {
		double rate;
		switch (stateCode) {
		case "TG":
			rate = 6.5;
			break;
		case "AP":
			rate = 7.5;
			break;
		default:
			rate = 8.5;
			break;
		}
		return amount * rate;
	}

	public static void main(String args[]) {

		// Method overloading
		int amount=10000;
		System.out.println("Tax for amount " + amount + " is " + calculateTax(amount));
		System.out.println("Tax for amount " + amount + " with percentage given " + calculateTax(amount, 11.5));
		System.out.println("Tax for given state " + calculateTax(amount, "TG"));

	}

}
