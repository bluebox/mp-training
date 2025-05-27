package SmartKitchenCompositionChallenge;

public class Main {
	public static void main(String [] args)
	{
		SmartKitchen kitchen1 = new SmartKitchen();
		
//		CASE-1: Setting appliance states individually")
		kitchen1.addWater();
		kitchen1.pourMilk();
		kitchen1.loadDishWasher();
		
//		CASE-2: Using doKitchenWork() which delegates the work to any of its appliances"
		kitchen1.setKitchenState(true,false, true);
		
		kitchen1.doKitchenWork();
		
	}
}
