package Project;

public class ProductB extends ProductForSale{
	public ProductB(double price, String description) {
		super("Product B", price, description);
	}
	
	public void showDetails() {
		System.out.println("Type: "+this.getType()+", Price: "+this.getPrice()+" Decription: "+this.getDescription());
	}
}