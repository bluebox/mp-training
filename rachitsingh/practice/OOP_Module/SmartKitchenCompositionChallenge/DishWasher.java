package SmartKitchenCompositionChallenge;

public class DishWasher {
	private boolean hasWorkToDo;
	
	public DishWasher()
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
	public void doDishes()
	{
		System.out.println("Your dishes are being washed......");
	}
}
