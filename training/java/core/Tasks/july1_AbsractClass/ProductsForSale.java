package corejava.july1_AbsractClass;

public abstract class ProductsForSale {
	private ProductType productType;
	private double productPrice;
	private String description;
	
	public ProductsForSale(ProductType type, double price, String description) {
		this.productType = type;
		this.productPrice = price;
		this.setDescription(description);
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	public double getSalesPrice(int quantity) {
		return quantity*this.productPrice;
	}
	
	public void printPricedItem(int quantity) {
		double linedPrice=getSalesPrice(quantity);
		System.out.println("Product: "+this.productType+", unitPrice: "+this.productPrice+" - [Quantity: "+quantity+", totalPrice: "+linedPrice+"]");
	}
	
	public abstract void showDetails();

	
	
	
}
