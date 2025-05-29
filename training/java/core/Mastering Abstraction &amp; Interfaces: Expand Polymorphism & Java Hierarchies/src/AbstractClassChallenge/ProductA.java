package AbstractClassChallenge;

public class ProductA extends ProductForSale{

	public ProductA(String type, double price, String description) {
		super(type, price, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("Product A - Type :"+type+" , Price : " +price+" , Description :"+description);
	}
	
}
