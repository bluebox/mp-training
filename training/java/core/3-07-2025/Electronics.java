public class Electronics extends Product {
	private int qty;

	public Electronics(String type, double price, String description,int qty) {
		super(type, price, description);
		this.qty=qty;
	}

	@Override
	public void showDetails() {
		System.out.println("this is electronic item");
		super.printPricedItem(qty);
	}

}

