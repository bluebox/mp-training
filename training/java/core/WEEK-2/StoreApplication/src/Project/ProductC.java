package Project;

public class ProductC extends ProductForSale{
	public ProductC(double price, String description) {
		super("Product C", price, description);
	}
	
	public void showDetails() {
		System.out.println("Type: "+this.getType()+", Price: "+this.getPrice()+" Decription: "+this.getDescription());
	}
}