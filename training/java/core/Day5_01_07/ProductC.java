package Day5_01_07;

public class ProductC extends ProductForSale{

	public ProductC(String type, double price, String description) {
		super(type, price, description);
	}

	@Override
	public void printPricedItem(int qty) {
		System.out.printf("%2d qty %8.2f eachof type %s",qty,price,type);
	}

	@Override
	public double getSalesPrice(int qty) {
		return qty*price;
	}

}
