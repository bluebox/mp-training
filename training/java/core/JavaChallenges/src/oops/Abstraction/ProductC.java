package oops.Abstraction;


public class ProductC extends ProductForSale{
	public ProductC(String type,double price,String desc) {
    	super(type,price,desc);
    }
	@Override
	void showDetaisl() {
		System.out.println("details of productC");
		System.out.println(this.type);
		System.out.println(this.price);
		System.out.println(this.description);
	}

}