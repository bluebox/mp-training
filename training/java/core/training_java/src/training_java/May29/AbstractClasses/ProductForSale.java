package AbstractClasses;

public abstract class ProductForSale {
	public String type;
	public double price;
	public String description;
	
	ProductForSale(String type,double price, String description){
		this.type=type;
		this.price=price;
		this.description=description;
	}
	public double getSalesPrice(int qty) {
		return qty*price;
	}
	
	public void printPricedItem(int qty)
	{
		System.out.println("The price for given"+ qty+ " is"+ getSalesPrice(qty));
	}
	abstract void showDetails();
}
