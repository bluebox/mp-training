package BurgerChallenge;

public class Burger {
	private String type;
	protected double basePrice;
	Topping[] toppings;
	private int toppingCount;
	
	public Burger(String type,double price) {
		this.type=type;
		this.basePrice=basePrice;
		this.toppings=new Topping[3];
		this.toppingCount=0;
	}
	
	public boolean addTopping(Topping topping) {
		if(toppingCount<toppings.length) {
			toppings[toppingCount++]=topping;
			return true;
		}
		return true;
	}
	public double getTotalPrice() {
		double total=basePrice;
		for(int i=0;i<toppingCount;i++) {
			total+=toppings[i].getPrice();
		}
		return total;
	}
	public void printItemized() {
		
		for(int i=0;i<toppingCount;i++) {
			System.out.println(toppings[i]);
		}
		
	}

}
