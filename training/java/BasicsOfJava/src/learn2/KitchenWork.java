package learn2;

public class KitchenWork {
	
	public static void main(String[] args) {
		
		Refrigerator iceBox = new Refrigerator();
		DishWasher dishWasher = new DishWasher();
		CoffeeMaker brewMaster = new CoffeeMaker();
		Kitchen work = new Kitchen(iceBox,dishWasher,brewMaster);
		work.setKitchenState(false, false, true);
		work.doKitchenWork();
		work.setKitchenState(false, false, false);
		work.doKitchenWork();
	}
}

class Kitchen{
	
	private Refrigerator iceBox;
	private DishWasher dishWasher;
	private CoffeeMaker brewMaster;
	public Kitchen(Refrigerator iceBox,DishWasher dishWasher,CoffeeMaker brewMaster) {
		
		this.iceBox = iceBox;
		this.dishWasher = dishWasher;
		this.brewMaster = brewMaster;
	}
	public void setKitchenState(boolean hasOrderedFood,boolean hasDoneDishes,boolean hasBrewedCoffee) {
		iceBox.setHasWorkToDo(hasOrderedFood);
		dishWasher.setHasWorkToDo(hasDoneDishes);
		brewMaster.setHasWorkToDo(hasBrewedCoffee);
//		doKitchenWork();
	}
	public void addWater() {
		System.out.println("adding Water");
	}
	public void pourmilk() {
		System.out.println("pour milk");
	}
	public void loadDishWasher() {
		System.out.println("dish washer loading");
	}
	public void doKitchenWork() {
		System.out.println("_________________________");
		iceBox.orderFood();
		dishWasher.doDishes();
		brewMaster.brewCoffee();
	}
}
class Refrigerator
{
	
	private boolean hasToDo;
	public void setHasWorkToDo(boolean hasToDo){
		this.hasToDo = hasToDo;
//		orderFood();
	}
	public void orderFood() {
		if(hasToDo) {
			System.out.println("Ordering Food");
			 hasToDo = false;
		}
	}
}

class DishWasher
{
	private boolean hasToDo;
	
	public void setHasWorkToDo(boolean hasToDo) {
		this.hasToDo = hasToDo;
//		doDishes();
	}
	
	public void doDishes() {
		if(hasToDo){
			System.out.println("doing Dishes");
			hasToDo = false;
		}
	}
}

class CoffeeMaker{
	private boolean hasToDo;
	
	public void setHasWorkToDo(boolean hasToDo) {
		this.hasToDo = hasToDo;
//		brewCoffee();
	}
	
	public void brewCoffee() {
		if(hasToDo){
			System.out.println("brewing coffee");
			hasToDo = false;
		}
	}
}