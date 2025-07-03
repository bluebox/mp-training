package Abstract;

public class OrderItem {
	private Product product;
	private int quantity;
	
	public OrderItem(Product product, int quantity) {
		this.product = product;
		this.quantity= quantity;
	}
	public void printItem()
	{
		System.out.println(product.getName()+" "+((product.getPrice())));
	}	
}
