package abst.lpa;

public class Clothing extends ProductForSale{
	
	public Clothing(String type,String descript,double cost) {
		super(type,descript,cost);
	}
	public void showDetails() {
		System.out.println("cloth type: "+getType());
		System.out.println("cloth Description: "+getDescript());
		System.out.println("cloth cost: "+getCost());
	}
}
