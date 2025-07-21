package Project;

public class ProductA extends ProductForSale{
	public ProductA(double price, String description) {
		super("Product A", price, description);
	}
	
	public void showDetails() {
		System.out.println("Type: "+this.getType()+", Price: "+this.getPrice()+" Decription: "+this.getDescription());
	}
}
