package SmartKitchenCompositionChallenge;

public class Refrigerator {
	private boolean hasWorkToDo;
	
	public Refrigerator()
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
	public void orderFood()
	{
		System.out.println("Your is order is in progress........");
	}
}
