package July1;

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

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getSalesPrice(int quantity) {
		return price*quantity;
	}
	
	public void printPricedItem(int quantity) {
		System.out.printf("%2d quantity at $%8.2f each, %-15s %-35s %n", quantity, price, type, description);
	}
	
	public abstract void showDetails();
}
