package Day5_01_07;

public class ProductA extends ProductForSale{

	public ProductA(String type, double price, String description) {
		super(type, price, description);
	}

	@Override
	public void printPricedItem(int qty) {
		System.out.printf("%2d qty %8.2f each of type %s",qty,price,type);
	} 

	@Override
	public double getSalesPrice(int qty) {
		return qty*price;
	}

}
