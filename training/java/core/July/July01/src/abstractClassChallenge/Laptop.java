package abstractClassChallenge;

public class Laptop extends ProductForSale{
	public Laptop(double price, String description) {
		super("Laptop", price, description);
	}

	@Override
	public String toString() {
		return "[Type=" + getType() + ", Price=" + getPrice() + ",Description=" + getDescription() +"]";
	}
	
	@Override
	public void showDetails() {
		System.out.println("type: "+getType()+"Price : "+getPrice()+" description : "+getDescription());
	}

}
