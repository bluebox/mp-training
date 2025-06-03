package oops.Abstraction;


public class ProductB extends ProductForSale{
	public ProductB(String type,double price,String desc) {
    	super(type,price,desc);
    }
	@Override
	void showDetaisl() {
		System.out.println("details of productB");
		System.out.println(this.type);
		System.out.println(this.price);
		System.out.println(this.description);
	}

}