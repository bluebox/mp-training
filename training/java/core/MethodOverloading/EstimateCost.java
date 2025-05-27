package MethodOverloading;

public class EstimateCost {
	public static double estimateCost(String location) {
		return 40.0;
	}

	public static double estimateCost(String location, double weight) {
		return 40.0 + weight * 10.5;
	}

	public static double estimateCost(String location, double weight, boolean isFastdelivery) {
		double price = 40.0 + weight * 2.5;
		return isFastdelivery ? price + 100.0 : price;
	}

	public static void main(String[] args) {

		System.out
				.println("Your MEDPLUS package is eligible for regular delivery of price " + estimateCost("Hyderabad"));
		System.out.println(
				"Your MEDPLUS package revised delivery cost for extra weight " + estimateCost("Hyderabad", 20.5));
		System.out.println(
				"Your MEDPLUS package revised delivery cost for fast delivery " + estimateCost("Hyderabad", 20.5, true));

	}

}
