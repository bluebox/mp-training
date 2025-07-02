package abstractClassChallenge;

public class Tv extends ProductForSale {

	public Tv(double price, String description) {
		super("Tv", price, description);
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
