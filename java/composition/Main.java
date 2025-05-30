package composition;

public class Main {
	public static void main(String[ ] args) {
		SmartKichen smartKitchen= new SmartKichen();
		smartKitchen.setKitchenState(true, false, true);
		smartKitchen.doKitchen();
	}
}
