package corejava.july1_AbsractClass;

public class Product2 extends ProductsForSale{
	public Product2(double price, String description) {
		super(ProductType.Product2, price, description);
	}

	@Override
	public void showDetails() {
		System.out.println("Product2:");
		System.out.println("Description: " +this.getDescription());
	    System.out.printf("Price: "+this.getProductPrice());
	}
}
