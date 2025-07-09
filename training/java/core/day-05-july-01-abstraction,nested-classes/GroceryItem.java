package day5;
public class GroceryItem extends ProductForSale {
	private int qty;

	public GroceryItem(String type, double price, String description, int qty) {
		super(type, price, description);
		this.qty = qty;
	}

	@Override
	public void showDetails() {
		System.out.println("this is grocery item");
		super.printPricedItem(qty);
	}

}
