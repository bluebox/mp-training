package abstractClassChallenge;

public class Phone extends ProductForSale{
	
	public Phone(double price, String description) {
		super("Phone", price, description);
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
