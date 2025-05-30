package oops.inheritance.smartKitchen;

public class SmartKichen {
	coffeMaker breCoffeMaker;
	DishWasher dishWasher;
	Refrigerater iceBox;
	public SmartKichen(coffeMaker breCoffeMaker,DishWasher dishWasher,Refrigerater iceBox) {
		this.breCoffeMaker=breCoffeMaker;
		this.dishWasher=dishWasher;
		this.iceBox=iceBox;
	}
	
	public void addWater() {
		iceBox.orderFood();
	}
	
	public void pourMilk() {
		breCoffeMaker.brewCoffe();
	}
	public void loadDishMaker() {
		dishWasher.toDishes();
	}
	public void setKitchenState(boolean water,boolean milk,boolean dishes) {
		iceBox.haswork=water;
		breCoffeMaker.hasWork=milk;
		dishWasher.haswork=dishes;
	}
	
	public void doKitchenWork() {
		if(breCoffeMaker.hasWork) {
			pourMilk();
		}
		else if(iceBox.haswork) {
			addWater();
		}
		if(dishWasher.haswork) {
			loadDishMaker();
		}
	}
}
