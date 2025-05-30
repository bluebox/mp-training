
public class SmartKitchen {
	CoffeeMaker brewMaster;
	DishWasher dishwasher;
	Refrigerator iceBox;
	public SmartKitchen(CoffeeMaker brewMaster,DishWasher dishwasher,
			Refrigerator iceBox) {
		this.brewMaster=brewMaster;
		this.dishwasher=dishwasher;
		this.iceBox=iceBox;
	}
	void addWater() {
		iceBox.orderFood();
	}
	void pourMilk() {
		brewMaster.brewCoffee();
	}
	void loadDishWasher() {
		dishwasher.toDishes();
	}
	void setKitchenState(boolean water,boolean milk,boolean dishes) {
		iceBox.hasWorkToDo=water;
		brewMaster.hasWorkToDo=milk;
		dishwasher.hasWorkToDo=dishes;
	}
	void doKitchenWork() {
		if(brewMaster.hasWorkToDo) {
			pourMilk();
		}
		if(iceBox.hasWorkToDo) {
			addWater();
		}
		if(dishwasher.hasWorkToDo) {
			loadDishWasher();
		}
	}
}
