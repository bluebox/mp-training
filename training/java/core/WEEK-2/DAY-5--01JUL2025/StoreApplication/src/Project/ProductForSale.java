package Project;

public abstract class ProductForSale {
	private String type;
	private double price;
	private String description;
	
	public ProductForSale(String type, double price, String description) {
		this.type=type;
		this.price=price;
		this.description=description;
	}
	
	public ProductForSale(String type) {
		this("unknown", 0, "Not Given");
	}
	
	public ProductForSale(String type, double price) {
		this(type, price, "Not Given");
	}
	
	public String printPricedItem(int quantity) {
		String output="[ "+quantity+" at "+this.price+" each\ntype: "+this.type+" description: "+this.description+" ]";
		return output;
	}
	public double getSalesPrice(int quantity) {
		return quantity*this.price;
	}
	public abstract void showDetails();

	public String getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}
	
	
	 
}
