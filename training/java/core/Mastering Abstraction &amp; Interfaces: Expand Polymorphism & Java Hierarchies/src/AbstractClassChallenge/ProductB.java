package AbstractClassChallenge;

public class ProductB extends ProductForSale{

	public ProductB(String type, double price, String description) {
		super(type, price, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("Product B - Type :"+type+" , Price : " +price+" , Description :"+description);
	}
	
}
