package corejava.july1_AbsractClass;

public class Product3 extends ProductsForSale{
	
	public Product3(double price, String description) {
		super(ProductType.Product3, price, description);
	}

	@Override
	public void showDetails() {
		System.out.println("Product3:");
		System.out.println("Description: " +this.getDescription());
	    System.out.printf("Price: "+this.getProductPrice());
	}
}
