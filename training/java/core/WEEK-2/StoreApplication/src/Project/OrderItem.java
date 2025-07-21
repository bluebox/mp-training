package Project;

public class OrderItem {
	private int quantity;
	private ProductForSale product;
	
	public OrderItem(ProductForSale product, int quantity) {
		this.product=product;
		this.quantity=quantity;
	}
	
	public OrderItem(ProductForSale product) {
		this(product, 1);
	}
	
	public int getQuantity() {
		return quantity;
	}

	public ProductForSale getProduct() {
		return product;
	}

	@Override
	public String toString() {
		return this.getProduct().printPricedItem(quantity);
	}
}
