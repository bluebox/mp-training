package AbstractClasses;

public class ProductC extends ProductForSale{
	public ProductC(String type,double price, String description){
		super(type,price,description);
	}
	
	public void showDetails() {
		System.out.println("The type: "+type+" The Price "+ price+ " The Description "+description);
	}

}


