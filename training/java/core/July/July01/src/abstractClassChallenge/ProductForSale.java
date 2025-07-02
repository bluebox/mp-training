package abstractClassChallenge;

public abstract class ProductForSale {
	
	private String type;
	private double price;
	private String description;
	
	public ProductForSale(String type, double price, String description) {
		
		this.type = type;
		this.price = price;
		this.description = description;
		
	}
	
	public String getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public void printPricedItem(int qty) {
		System.out.printf("%d qty at $%.2f each, %s %s %n",qty,price,type,description);
	}
	
	public double getSalesPrice(int qty) {
		return price * qty;
	}
	
	public abstract void showDetails();
	
}
