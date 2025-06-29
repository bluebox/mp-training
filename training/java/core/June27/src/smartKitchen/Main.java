package smartKitchen;

public class Main {

	public static void main(String[] args) {
		Refrigerator refrigerator = new Refrigerator(false);
		DishWasher dishWasher = new DishWasher(false);
		CoffeeMaker coffeeMaker = new CoffeeMaker(false);
		SmartKitchen smartKitchen = new SmartKitchen( coffeeMaker, dishWasher, refrigerator);
		
		smartKitchen.addWater();
		smartKitchen.doKitchenWork();

	}

}
