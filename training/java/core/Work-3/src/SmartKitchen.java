
public class SmartKitchen {
	private Refrigerator refrigerator;
	private DishWasher dishwasher;
	private CoffeeMaker coffeemaker;
	
	public SmartKitchen() {
		refrigerator=new Refrigerator();
		dishwasher=new DishWasher();
		coffeemaker=new CoffeeMaker();
	}
	
	public void addWater() {
		coffeemaker.setHasWorkToDo(true);
	}
	
	public void pourMilk() {
		refrigerator.setHasWorkToDo(true);
	}
	
	public void loadDishWasher() {
		dishwasher.setHasWorkToDo(true);
	}
	
	public void setKitchenState(boolean b1,boolean b2,boolean b3) {
		this.refrigerator.setHasWorkToDo(b1);
		this.dishwasher.setHasWorkToDo(b2);
		this.coffeemaker.setHasWorkToDo(b3);
	}
	
	public void doKitchenWork() {
		refrigerator.orderFood();
		dishwasher.doDishes();
		coffeemaker.brewCoffee();
	}
}

