package com.tulasidhar.june27.SmartKichenComposition;

public class SmartKitchen {
	private Refrigerator iceBox;
	private DishWasher dishWasher;
	private CoffeeMaker brewMaster;
	
	
	
	public SmartKitchen() {
        this.iceBox = new Refrigerator();
        this.dishWasher = new DishWasher();
        this.brewMaster = new CoffeeMaker();
    }

	public void setKitchenState(boolean fridge , boolean dish , boolean coffee) {
		iceBox.setHasWorkTodo(fridge);
		dishWasher.setHasWorkTodo(dish);
		brewMaster.setHasWorkTodo(coffee);
	}
	
	public void addWater() {
		brewMaster.setHasWorkTodo(true);
	}
	public void pourMilk() {
		iceBox.setHasWorkTodo(true);
	}
	public void loadDishWasher() {
		dishWasher.setHasWorkTodo(true);
	}
	
	public void doKitchenWork() {
		iceBox.orderFood();
		dishWasher.doDishes();
		brewMaster.brewCoffee();
	}
}

class Refrigerator{
	boolean hasWorkTodo;
	
	public boolean HasWorkTodo() {
		return hasWorkTodo;
	}

	public void setHasWorkTodo(boolean hasWorkTodo) {
		this.hasWorkTodo = hasWorkTodo;
	}

	public void orderFood() {
		if(hasWorkTodo) {
			System.out.println("Ordered Food");
			setHasWorkTodo(false);
		}
		else {
			System.out.println("cannot order right now");
		}
	}
	
}

class DishWasher{
	boolean hasWorkTodo;
	
	public boolean HasWorkTodo() {
		return hasWorkTodo;
	}

	public void setHasWorkTodo(boolean hasWorkTodo) {
		this.hasWorkTodo = hasWorkTodo;
	}

	public void doDishes() {
		if(hasWorkTodo) {
			System.out.println("Dishes are done");
			setHasWorkTodo(false);
		}
		else {
			System.out.println("cannot do dishes right now");
		}
	}
	
}

class CoffeeMaker{
	boolean hasWorkTodo;
	
	public boolean HasWorkTodo() {
		return hasWorkTodo;
	}

	public void setHasWorkTodo(boolean hasWorkTodo) {
		this.hasWorkTodo = hasWorkTodo;
	}

	public void brewCoffee() {
		if(hasWorkTodo) {
			System.out.println("Coffee Brewing done");
			setHasWorkTodo(false);
		}
		else {
			System.out.println("cannot brew right now");
		}
	}
	
}

