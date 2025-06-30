package corejava.june27_Inheritence;

public class SmartKitchen {
	private Refrigerator iceBox;
    private DishWasher dishWasher;
    private CoffeeMaker brewMaker;

    public SmartKitchen() {
        this.iceBox = new Refrigerator(false);
        this.dishWasher = new DishWasher(false);
        this.brewMaker = new CoffeeMaker(false);
    }
	
	public void addWater() {
		iceBox=new Refrigerator(true);
	}
	public void pourMilk() {
		brewMaker=new CoffeeMaker(true);
	}
	
	public void loadDishWasher() {
		dishWasher=new DishWasher(true);
	}
	
	 public void setKitchenState(boolean fridgeWork, boolean coffeeWork, boolean dishWork) {
	        iceBox.setHasWorkToDo(fridgeWork);
	        brewMaker.setHasWorkToDo(coffeeWork);
	        dishWasher.setHasWorkToDo(dishWork);
	 }
	 public void doKitchenWork() {
	        iceBox.orderFood();
	        brewMaker.brewCoffee();
	        dishWasher.doDishes();
	    }
	
	
}
