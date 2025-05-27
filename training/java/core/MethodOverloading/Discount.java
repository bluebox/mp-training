package MethodOverloading;

public class Discount {
	public static double applyDiscount(double price, double percentage) {
		return price - (price * percentage / 100.0);
	}

	public static double applyDiscount(double price, int flatOff) {
		return price - flatOff;
	}

	public static double applyDiscount(double price, String couponCode) {
		if (couponCode == "MEDPLUS100") {
			System.out.println("Wow!! You will get $100 off on your bill");
			return price - 100.0;
		}
		if (couponCode == "MEDPLUS200") {
			System.out.println("Wow!! You will get $200 off on your bill");
			return price - 200.0;
		}
		return price-0;
	}

	public static void main(String args[]) {
		int price=2560;
		String couponCode="MEDPLUS100";
		System.out.println("You got discount of 12.5% on you medicines on Medplus,it your final bill $"+applyDiscount(price,12.5));
		System.out.println("You Flat 150 Off on you medicines on Medplus,it your final bill $"+applyDiscount(price,150));
		System.out.println("Here is your bill after coupon code of Medplus $"+applyDiscount(price,couponCode));

	}

}
