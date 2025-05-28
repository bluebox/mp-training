package abstractexamples;

public abstract class ProductForSale {
	String type;
	public ProductForSale(String type, double price, String description) {
		super();
		this.type = type;
		this.price = price;
		this.description = description;
	}
	double price;
	String description;
	
	public double getSalesPrice(int quantity)
	{
		return quantity * price; 
	}
	public void printPrice(int quantity)
	{
		System.out.println("for "+quantity+ " quantities price = "+ quantity * price);
	}
	public abstract void showDetails();
	

}
