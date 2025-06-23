package learn2;

public abstract class ProductForSale {
	
	private String type;
	private double price;
	private String description;
	
	public double getSalesPrice(int quantity) {
		
		return price * quantity;
	}
	
	public double getSalesPrice() {
		
		return (getSalesPrice(1));
	}
	
	public void printPricedItem(int quantity) {
		
		double totalPrice = quantity * price;
		System.out.println("The type is "+type+" \ndecription : " + description + "quantity : "
				+ quantity + " \neach of price " + price + "\n total price is " + totalPrice);
	}
	
	abstract void showDetails();
}
