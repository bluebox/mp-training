package abstraction;

public class Product1 extends ProductsForSale {
	
	

	

	public Product1(String type, double price, String description) {
		super(type, price, description);
	}

	@Override
	public void showDetails() {
		
		System.out.println("Product details :: "+"\nType :: "+super.getType()+"\nPrice :: "+super.getPrice()+"\nDescription :: "+super.getDescription());
		
	}


}
