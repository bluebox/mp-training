package composition;

public class SmartKitchen {
	
	private Refrigerator ref;
	private CoffeeMaker brewMaster;
	private DishWasher washer;
	
	public SmartKitchen()
	{
		ref = new Refrigerator();
		brewMaster = new CoffeeMaker();
		washer = new DishWasher();
	}
	
	public void addWater()
	{
		ref.setHasWorkToDo(true);
		System.out.println("Added water");
	}
	
	public void pourMilk()
	{
		brewMaster.setHasWorkToDo(true);
		System.out.println("Added Milk");
	}
	
	public void loadDishWasher()
	{
		washer.setHasWorkToDo(true);
		System.out.println("Loading Dish washer");
	}
	
	public setKitcenState(boolean , )

}
