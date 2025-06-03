package BurgerChallenge;

public class Drink {
	private String type;
	private String size;
	private double price;
	
	public Drink(String type,String size) {
		this.type=type;
		this.size=size;
		
		switch(size.toLowerCase()) {
		case "small":price=1.0;break;
		case "medium":price=2.0;break;
		case "large" :price=2.5;break;
		}
	}
	
	public double getPrice() {
		return price;
	}
	
	public void itemized() {
		System.out.println("Burger type: "+type+"Size: "+size+"price: "+price);
	}
}
