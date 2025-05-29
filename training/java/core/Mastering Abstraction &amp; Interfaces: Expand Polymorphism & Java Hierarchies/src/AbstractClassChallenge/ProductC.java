package AbstractClassChallenge;

public class ProductC extends ProductForSale{

	public ProductC(String type, double price, String description) {
		super(type, price, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("Product C - Type :"+type+" , Price : " +price+" , Description :"+description);
	}
	
}
