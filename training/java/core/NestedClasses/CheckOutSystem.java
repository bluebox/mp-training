package NestedClasses;

public class CheckOutSystem {
	
	public void applyDiscount(double price,String coupon) {
		
		class DiscountCalculator{
			public double getDiscountedPrice() {
				
				switch(coupon.toLowerCase()) {
				case "first80" :return price*0.80; 
				case "elite" :return price*0.70;
				case "summer":return price*0.1;
				default: return price;
				}
			}
		}
		DiscountCalculator calculator =new DiscountCalculator();
		double finalPrice=calculator.getDiscountedPrice();
		System.out.println("Discounted price: "+finalPrice);
		
	}
	public static void main(String[] args) {
		CheckOutSystem checkOut=new CheckOutSystem();
		checkOut.applyDiscount(10000.0, "elite");
		checkOut.applyDiscount(10000.0, "first80");
		checkOut.applyDiscount(10000.0, "summer90");

		
	}
}
