package AbstractClasses;

public class ProductB extends ProductForSale{
	public ProductB(String type,double price, String description){
		super(type,price,description);
	}
	
	public void showDetails() {
		System.out.println("The type: "+type+" The Price "+ price+ " The Description "+description);
	}

}

