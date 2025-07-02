package Day_1_7_25;

public class Pen extends ProductForSale{

	public Pen(String type, double price, String description) {
		super(type, price, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("type"+this.getType()+"price "+this.getPrice()+"Description"+this.getDescription());
	}
  
}
