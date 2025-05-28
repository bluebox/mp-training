package learn2;

public class KitchenWork {
	
	public static void main(String[] args) {
		
		Kitchen work = new Kitchen(true,false,true);
	}
}

class Kitchen{
	
	private Refrigerator iceBox;
	private DishWasher dishWasher;
	private CoffeeMaker brewMaster;
	public Kitchen(boolean hasOrderedFood,boolean hasDoneDishes,boolean hasbrewedCoffee) {
		setKitchenState(hasOrderedFood,hasDoneDishes,hasbrewedCoffee);
		
	}
	public Kitchen() {
		doKitchenWork();
	}
	public void setKitchenState(boolean hasOrderedFood,boolean hasDoneDishes,boolean hasbrewedCoffee) {
		iceBox = new Refrigerator(hasOrderedFood);
		dishWasher = new DishWasher(hasDoneDishes);
		brewMaster = new CoffeeMaker(hasbrewedCoffee);
		
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
	}
}
class Refrigerator extends Kitchen{
	
	private boolean hasToDo;
	public Refrigerator(boolean hasToDo){
		this.hasToDo = hasToDo;
		orderFood();
	}
	public void orderFood() {
		if(hasToDo) {
			System.out.println("Ordering Food");
			this.addWater();
		}
	}
}

class DishWasher extends Kitchen{
	private boolean hasToDo;
	
	public DishWasher(boolean hasToDo) {
		this.hasToDo = hasToDo;
		doDishes();
	}
	
	public void doDishes() {
		if(hasToDo){
			System.out.println("doing Dishes");
			this.loadDishWasher();
		}
	}
}

class CoffeeMaker extends Kitchen{
	private boolean hasToDo;
	
	public CoffeeMaker(boolean hasToDo) {
		this.hasToDo = hasToDo;
		brewCoffee();
	}
	
	public void brewCoffee() {
		if(hasToDo){
			System.out.println("brewing coffee");
			this.pourmilk();
		}
	}
}