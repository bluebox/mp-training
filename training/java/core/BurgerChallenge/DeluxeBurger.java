package BurgerChallenge;

public class DeluxeBurger extends Burger {
	
	private int toppingCount=0;
	public DeluxeBurger() {
		super("Delux",20.00);
		this.toppings=new Topping[5];
		
	}
	@Override
	public boolean addTopping(Topping topping) {
		if(toppingCount<toppings.length) {
			toppings[toppingCount++]=topping;
			return true;
		}
		return true;
	}
	@Override
	public double getTotalPrice() {
		
		return basePrice;
	}
	@Override
	public void printItemized() {
		
		for(int i=0;i<toppingCount;i++) {
			System.out.println(toppings[i]);
		}
		
	}
	

}
