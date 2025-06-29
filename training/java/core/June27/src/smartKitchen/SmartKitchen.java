package smartKitchen;

public class SmartKitchen {
	
	private CoffeeMaker brewMaster;
	private DishWasher dishWasher;
	private Refrigerator iceBox;
	
	public SmartKitchen(CoffeeMaker brewMaster, DishWasher disWasher, Refrigerator iceBox) {
		this.brewMaster = brewMaster;
		this.dishWasher = disWasher;
		this.iceBox = iceBox;
	}
	
	public void addWater() {
		brewMaster.setHasWorkToDo(true);
	}
	
	public void pourMilk() {
		iceBox.setHasWorkToDo(true);
	}
	
	public void loadDishWasher() {
		dishWasher.setHasWorkToDo(true);
	}
	
	public void setKitchenState(boolean refrigerator , boolean dish , boolean coffe) {
		
		iceBox.setHasWorkToDo(refrigerator);
		dishWasher.setHasWorkToDo(dish);
		brewMaster.setHasWorkToDo(coffe);
		
	}
	
	public void doKitchenWork() {
		iceBox.orderFood();
		dishWasher.doDishes();
		brewMaster.brewCoffee();
	}
	
}
