package corejava.july1_AbsractClass;

public class Product1 extends ProductsForSale {
	public Product1(double price, String description) {
		super(ProductType.Product1, price, description);
	}

	@Override
	public void showDetails() {
		System.out.println("Product1:");
		System.out.println("Description: " +this.getDescription());
	    System.out.printf("Price: "+ this.getProductPrice());
	}
}
