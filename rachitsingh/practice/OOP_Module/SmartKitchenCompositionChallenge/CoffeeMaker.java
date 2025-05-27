package SmartKitchenCompositionChallenge;

public class CoffeeMaker {
	private boolean hasWorkToDo;
	
	public CoffeeMaker()
	{
		hasWorkToDo = false;
	}
	public void setStatus(boolean hasWorkToDo)
	{
		this.hasWorkToDo = hasWorkToDo;
	}
	public boolean getStatus()
	{
		return hasWorkToDo;
	}
	public void brewCoffee()
	{
		System.out.println("Brewing your coffee......");
	}
}
