package Day5;

public abstract class ProductForSale {
	protected String type;
	protected double price;
	protected String description;
	
	public ProductForSale(String type,double price, String description) {
		this.type=type;
		this.price=price;
		this.description=description;
	}
	
	public double getSalesPrice(int quant) {
		return price*quant;
	}
	
	public abstract void showDetails();
	
	public void printPricedItem(int quant) {
		System.out.println("Price : "+price+" of type : "+type+" ordered : "+quant+" and it's description : "+description);
	}
}


class Electronics extends ProductForSale{

	public Electronics(String type, double price, String description) {
		super(type, price, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("type : "+type+" price : "+price+" description : "+description);
	}
}

class Furniture extends ProductForSale{

	public Furniture(String type, double price, String description) {
		super(type, price, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("type : "+type+" price : "+price+" description : "+description);
	}
}

class Clothing extends ProductForSale{

	public Clothing(String type, double price, String description) {
		super(type, price, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("type : "+type+" price : "+price+" description : "+description);
	}
}
