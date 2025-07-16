package corejava.july1_AbsractClass;

public record OrderItem(int productQuantity,ProductsForSale product) {
	public void printItems() {
		product.printPricedItem(productQuantity);
	}
	
	public double getTotal() {
		return product.getSalesPrice(productQuantity);
	}
	
}
