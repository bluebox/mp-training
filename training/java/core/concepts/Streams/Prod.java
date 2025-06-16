package JAVAtraining;

public class Prod {
	int productID;
	String productName;
	int quantity;
	int cost;
	public Prod(int productID, String productName, int cost) {
		this.productID = productID;
		this.productName = productName;
		this.cost = cost;
	}
	public Prod(int productID, String productName, int quantity, int cost) {
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
		this.cost = cost;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Prod [productID=" + productID + ", productName=" + productName + ", quantity=" + quantity + ", cost="
				+ cost + "]";
	}
	
}
