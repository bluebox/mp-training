package POJOs;

public class Products {
	private String productName;
	private double productPrice;
	private int productQuantity;
	
	
	public Products(String productName, double productPrice, int productQuantity) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;

	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice=productPrice;
	}

	public int getProductQuamtity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity=productQuantity;
	}

	@Override
	public String toString() {
		String item = "Product Name: "+productName + "\n" + "price: "+productPrice + "\n" +"Qunatity: " +productQuantity;
		return item;
	}
}
