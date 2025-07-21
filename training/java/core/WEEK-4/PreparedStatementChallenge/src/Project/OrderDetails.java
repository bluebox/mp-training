package Project;

public class OrderDetails {
	private String productName;
	private int quantity;
	
	public OrderDetails(String productName, int quantity) {
		super();
		this.productName = productName;
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	
}
