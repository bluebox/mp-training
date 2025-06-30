package corejava.june27_Inheritence;

public class Kitchen {

	public static void main(String[] args) {
		SmartKitchen smartKitchen = new SmartKitchen();
        smartKitchen.setKitchenState(true, true, false);
        smartKitchen.doKitchenWork();
		
	}

}
