package BurgerChallenge;

public class SideItem {

	private String type;
	private double price;
	
	public SideItem(String type,double price) {
		this.type=type;
		this.price=price;
	}
	
	public double getyPrice() {
		return price;
	}
	public void itemized() {
		System.out.println("Type: "+type+"Price: "+price);
	}
}
