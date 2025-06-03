package BurgerChallenge;

public class Topping {
	private String name;
	private double price;
	
	public Topping(String name,double price) {
		this.name=name;
		this.price=price;
	}
	
	public double getPrice() {
		return price;
		
	}
	@Override
	public String toString() {
		return "Price of Topping: "+price;
	}

}
