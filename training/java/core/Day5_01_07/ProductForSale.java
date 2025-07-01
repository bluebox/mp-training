package Day5_01_07;

public abstract class ProductForSale {
	String type;
	double price;
	String description;
	public abstract void printPricedItem(int qty);
	public abstract double getSalesPrice(int qty);
	public ProductForSale(String type, double price, String description) {
		this.type = type;
		this.price = price;
		this.description = description;
	}
	public void showDetails() {
		System.out.printf("""
				Type:%s
				Price:%d
				Description:%s
				""",type, price,description);
	}

	
}
