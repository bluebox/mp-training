package com;

class Refrigerator {
	private boolean hasToDo;
	
	public void setState(boolean setRefrigerator) {
		hasToDo = setRefrigerator;
	}
	
	
}

class CoffeeMaker {
	private boolean hasToDo;
}

class DishWasher {
	private boolean hasToDo;
}

public class SmartKitchen {
	
	private Refrigerator iceBox;
	private CoffeeMaker brewMaster;
	private DishWasher dishWasher;
	
	public void setKitchenState(boolean setRefrigerator, boolean setCoffeeMaker, boolean setDishWasher) {
		iceBox.setState(setRefrigerator);
		brewMaster.setState(setCoffeeMaker);
		dishWasher.setState(setDishWasher);
	}
	
	public static void main(String[] args) {
		SmartKitchen smartKitchen = new SmartKitchen();
		smartKitchen.setKitchenState(true, false, false);
	}
	
}
