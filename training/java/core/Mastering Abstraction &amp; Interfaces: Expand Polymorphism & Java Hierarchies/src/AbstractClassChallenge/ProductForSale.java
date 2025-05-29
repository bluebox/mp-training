package AbstractClassChallenge;

public abstract class ProductForSale {
	String type;
	double price;
	String description;
	public ProductForSale(String type, double price, String description) {
		super();
		this.type = type;
		this.price = price;
		this.description = description;
	}
	public double getSalesPrice(int quantity)
	{	
		return quantity*price;
	}
	public void printPricedItem(int quantity)
	{
		System.out.printf("%d x %s = %.2f \n",quantity,type,getSalesPrice(quantity));
	}
	public abstract void showDetails();
}
