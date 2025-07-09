package Day5_01_07;


public class ProductB extends ProductForSale{

	public ProductB(double price, String description) {
		super("B", price, description);
	}

	@Override
	public void printPricedItem(int qty) {
		System.out.printf("%2d qty %8.2f each od type %s",qty,price,type);
	}

	@Override
	public double getSalesPrice(int qty) {
		return qty*price;
	}

}
