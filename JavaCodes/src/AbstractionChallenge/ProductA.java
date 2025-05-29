package AbstractionChallenge;

public class ProductA extends ProductForSale{
    public ProductA(String type,double price,String desc) {
    	super(type,price,desc);
    }
	@Override
	void showDetaisl() {
		System.out.println("details of productA");
		System.out.println(this.type);
		System.out.println(this.price);
		System.out.println(this.description);
	}

}
