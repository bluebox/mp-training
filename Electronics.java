package abst.lpa;

public class Electronics extends ProductForSale{
	
	public Electronics(String type,String descript,double cost) {
		super(type,descript,cost);
	}
	
	public void showDetails() {
		System.out.println("cloth type: "+ getType());
		System.out.println("cloth cost: "+ getCost());
		System.out.println("cloth description: "+ getDescript());
	}
}