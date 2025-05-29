
public class Main {

	public static void main(String[] args) {
		CoffeeMaker brewMaster=new CoffeeMaker();
		DishWasher dishwasher=new DishWasher();
		Refrigerator iceBox=new Refrigerator();
		SmartKitchen s=new SmartKitchen(brewMaster,dishwasher,iceBox);
		s.setKitchenState(true, false, true);
		s.doKitchenWork();
		s.addWater();
		s.pourMilk();
		s.loadDishWasher();
	}
}
