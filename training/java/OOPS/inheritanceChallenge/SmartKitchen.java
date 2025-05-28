package inheritanceChallenge;

public class SmartKitchen {
	CoffeeMaker brewMaster;
	DishWasher dishWasher;
	Refrigerator iceBox;
	
	SmartKitchen(CoffeeMaker brewMaster,DishWasher dishWasher,Refrigerator iceBox)
	{
		this.brewMaster=brewMaster;
		this.dishWasher=dishWasher;
		this.iceBox=iceBox;
	}
	
	public void addWater()
	{
		iceBox.hasWorkToDo=true;
	}
	public void pourMilk()
	{
		brewMaster.hasWorkToDo=true;
	}
	public void loadDishwasher()
	{
		dishWasher.hasWorkToDo=true;

	}
	
	public void setKitchenState(boolean fridge,boolean coffee,boolean dishes)
	{
		iceBox.hasWorkToDo=fridge;
		dishWasher.hasWorkToDo=dishes;
		brewMaster.hasWorkToDo=coffee;
	}
	public void doKitchenWork()
	{
		if(iceBox.hasWorkToDo)
		iceBox.orderFood();
		if(dishWasher.hasWorkToDo)
		dishWasher.doDishes();
		if(brewMaster.hasWorkToDo)
		brewMaster.brewCoffee();
	}
	public static void main(String[] args) {
		CoffeeMaker brewMaster= new CoffeeMaker();
		DishWasher dishWasher= new DishWasher();
		Refrigerator iceBox= new Refrigerator();
		SmartKitchen kitchen = new SmartKitchen(brewMaster,dishWasher, iceBox);
		kitchen.setKitchenState(true, true, true);
		kitchen.doKitchenWork();

	}

}
