package com;

class Refrigerator implements Appliance {
	
	private boolean hasToDo;
	
	public void setState(boolean setRefrigerator) {
		hasToDo = setRefrigerator;
	}
	
	public boolean getState() {
		return hasToDo;
	}
	
	public void orderFood() {
		System.out.println("Ordering food...");
	}
	
}



class CoffeeMaker implements Appliance{
	
	private boolean hasToDo;
	
	public void setState(boolean state) {
		hasToDo = state;
	}
	
	public boolean getState() {
		return hasToDo;
	}
	
	public void brewCoffee() {
		System.out.println("Brewing coffee...");
	}
	
}



class DishWasher implements Appliance {
	
	private boolean hasToDo;
	
	public void setState(boolean state) {
		hasToDo = state;
	}
	
	public boolean getState() {
		return hasToDo;
	}
	
	public void doDishes() {}
	public static void getKitchenState(EnhancedSmartKitchen smartKitchen) {
        System.out.println("The running appliances in the Enhanced smart kitchen are...");
        for (Appliance appliance : smartKitchen.appliances) {
            if (appliance.getState()) {
                System.out.println(appliance.getClass().getSimpleName());
            }
        }
    }
		
		System.out.println("Cleaning dishes...");
	}
	
}



public class SmartKitchen {
	
	private Refrigerator iceBox;
	private CoffeeMaker brewMaster;
	private DishWasher dishWasher;
	
	public SmartKitchen(Refrigerator refrigerator, CoffeeMaker coffeeMaker, DishWasher dishWasher) {
		this.iceBox = refrigerator;
		this.brewMaster = coffeeMaker;
		this.dishWasher = dishWasher;
	}
	
	public void setKitchenState(boolean setRefrigerator, boolean setCoffeeMaker, boolean setDishWasher) {
		iceBox.setState(setRefrigerator);
		brewMaster.setState(setCoffeeMaker);
		dishWasher.setState(setDishWasher);
	}
	
	public String toString() {
		return "This is the smart kitchen object";
	}
	
	public static void getKitchenState(SmartKitchen smartKitchen) {
		System.out.println("The running appliances in the smart kitchen are...");
		if(smartKitchen.iceBox.getState()) System.out.println("Icebox");
		if(smartKitchen.brewMaster.getState()) System.out.println("Coffee Maker");
		if(smartKitchen.dishWasher.getState()) System.out.println("Dish Washer");
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		SmartKitchen smartKitchen = new SmartKitchen(new Refrigerator(), new CoffeeMaker(), new DishWasher());
		smartKitchen.setKitchenState(true, true, false);
		SmartKitchen.getKitchenState(smartKitchen);
	}
	
}
