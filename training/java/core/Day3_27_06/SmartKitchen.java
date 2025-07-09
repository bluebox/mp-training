package Day3_27_06;

public class SmartKitchen {
	private CoffeeMaker brewMaster=new CoffeeMaker();
	private DishWasher dishWasher=new DishWasher();
	private Refregirator iceBox=new Refregirator();
	public void addMilk() {
		brewMaster.setHasWorkToDo(true);
	}
	public void pourMilk() {
		iceBox.setHasWorkToDo(true);
	}
	public void loadDishWasher() {
		dishWasher.setHasWorkToDo(true);
	}
	public void setKitcheState(boolean coffee,boolean dish,boolean fridge) {
		brewMaster.setHasWorkToDo(coffee);
		if(coffee) {
			brewMaster.brewCoffee();
		}
		iceBox.setHasWorkToDo(dish);
		if(dish) {
			dishWasher.doDishes();
		}
		dishWasher.setHasWorkToDo(fridge);
		if(fridge) {
			iceBox.orderFood();
		}
	}
	public static void main(String args[]) {
		SmartKitchen sk=new SmartKitchen();
		sk.setKitcheState(true,true,true);
	}
}
