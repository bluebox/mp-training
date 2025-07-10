package June27;

public class SmartKitchen {
	
	private CoffeeMaker brewMaster;
	private DishWasher dishwasher;
	private Refrigerator iceBox;
	
	public SmartKitchen(CoffeeMaker brewMaster, DishWasher dishwasher, Refrigerator iceBox) {
		this.brewMaster = brewMaster;
		this.dishwasher = dishwasher;
		this.iceBox = iceBox;
	}
	
	public void addWater() {
		brewMaster.setHasWorkToDo(true);
	}	
	
	public void pourMilk() {
		iceBox.setHasWorkToDo(true);
	}
	
	public void loadDishWasher() {
		dishwasher.setHasWorkToDo(true);
	}
	
	public void setKitchenState(boolean value1,boolean value2,boolean value3) {
		if(value1) {
			addWater();
		}
		if(value2) {
			pourMilk();
		}
		if(value3) {
			loadDishWasher();
		}
	}
}
