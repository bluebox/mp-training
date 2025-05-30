package composition;

public class SmartKichen {
	CoffeeMaker coffeemaker=new CoffeeMaker();
	DishWasher dishwasher=new DishWasher();
	Refridgerator refridgerator=new Refridgerator();
	public void addWater() {
		System.out.println("Adding Water");
	}
	public void addMilk() {
		System.out.println("Adding milk");
	}
	public void loadDishWasher() {
		System.out.println("waiting for dishes");
	}
	public void setKitchenState(boolean state1, boolean state2, boolean state3) {
		coffeemaker.setStatus(state1);
		dishwasher.setStatus(state2);
		refridgerator.setStatus(state3);

	}
	public void doKitchen(){
		if(dishwasher.getStatus()) {
			dishwasher.doDishes();
		}
		else {
			loadDishWasher();
		}
		if(coffeemaker.getStatus())
		{
			coffeemaker.brewCoffee();
		}
		else {
			addWater();
		}
		if(refridgerator.getStatus()) {
			refridgerator.orderFood();
		}
		else {
			addMilk();
		}
		
	}

}
