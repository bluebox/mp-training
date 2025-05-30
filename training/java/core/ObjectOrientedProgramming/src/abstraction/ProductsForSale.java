package abstraction;

public abstract class ProductsForSale {
	
	private String type;
	private double price;
	private String description;
	
	
	
	public ProductsForSale(String type, double price, String description) {
		super();
		this.type = type;
		this.price = price;
		this.description = description;
	}

	public double getSalesPrice(int quantity)
	{
		return price*quantity;
	}
	
	public void printPricedItem(int quantity)
	{
		System.out.println("Type :: "+type+" qunatity :: "+quantity);
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public abstract void showDetails();
}
