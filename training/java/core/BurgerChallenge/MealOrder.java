package BurgerChallenge;

public class MealOrder {

	private Burger burger;
	private Drink drink;
	private SideItem sideItem;

	public MealOrder() {
		this.burger = new Burger("Bungy", 99.0);
		this.drink = new Drink("coke", "small");
		this.sideItem = new SideItem("fries", 78.0);
	}

	public MealOrder(Burger burger, Drink drink, SideItem sideItem) {
		this.burger = burger;
		this.drink = drink;
		this.sideItem = sideItem;
	}

	public double getTotalPrice() {
		return burger.getTotalPrice() + drink.getPrice() + sideItem.getyPrice();
	}

	public void receipt() {
		System.out.println("--Order summary--");
		burger.printItemized();
		drink.itemized();
		sideItem.itemized();
		System.out.println("Total Price: "+getTotalPrice());
	}
	public Burger getBurger() {
		return burger;
	}
	
	//main function
	public static void main(String args[]) {
		MealOrder newmeal=new MealOrder();
		newmeal.receipt();
		System.out.println("\n");
		Burger newBurger=new Burger("Veg",99.00);
		newBurger.addTopping(new Topping("veggies",10.0));
		newBurger.addTopping(new Topping("cheese",10.0));
		newBurger.addTopping(new Topping("spice",10.0));

		Drink newDrink=new Drink("pepsi","large");
		SideItem item=new SideItem("fries",50.0);
		MealOrder meal=new MealOrder(newBurger,newDrink,item);
		
		meal.receipt();
		
		
	}
}
