package AbstractClasses;

public class ProductA extends ProductForSale{
	public ProductA(String type,double price, String description){
		super(type,price,description);
	}
	
	public void showDetails() {
		System.out.println("The type: "+type+" The Price "+ price+ " The Description "+description);
	}

}
