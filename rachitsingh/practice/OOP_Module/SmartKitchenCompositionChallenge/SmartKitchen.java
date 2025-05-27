package SmartKitchenCompositionChallenge;

public class SmartKitchen {
	private CoffeeMaker brewMaster = new CoffeeMaker();
	private DishWasher dishWasher = new DishWasher();
	private Refrigerator iceBox = new Refrigerator();
	
	public void addWater()
	{
		brewMaster.setStatus(true);
	}
	public void pourMilk()
	{
		iceBox.setStatus(true);
	}
	public void loadDishWasher()
	{
		dishWasher.setStatus(true);
	}
	
	public void setKitchenState(boolean coffeeMakerStat, boolean refriStat, boolean dishWasherStat)
	{
		brewMaster.setStatus(coffeeMakerStat);
		iceBox.setStatus(refriStat);
		dishWasher.setStatus(dishWasherStat);
	}
	
	public void doKitchenWork()
	{
		brewMaster.brewCoffee();
		iceBox.orderFood();
		dishWasher.doDishes();
	}
}
