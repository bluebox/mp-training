package Day3;
abstract class machine{
	private boolean hasworktodo;
 	
	public boolean get() {
		return hasworktodo;
	}
	public void set(boolean value){
		this.hasworktodo=value;
	}
}

class Refrigerator extends machine{
	public Refrigerator() {
		System.out.println("this constructor was called");
	}
	
	public void orderFood() {
	if(get())System.out.println("The Food has been ordered Successfully.");
	}
}
class DishWasher extends machine{
	public DishWasher() {
		
	}
	public void doDishes() {
		if(get())System.out.println("The Dishes has been done Successfully.");
	}
}

class CoffeeMaker extends machine{
public CoffeeMaker() {
		
	}	
	
	public void brewCoffee() {
		if(get()) {
		System.out.println("The Coffee is brewed Successfully.");
		}
	}
		
}


public class SmartKitchen {
     
	private Refrigerator iceBox;
	private DishWasher dishwasher;
	private CoffeeMaker brewMaster;
	
	public SmartKitchen(Refrigerator fridge,CoffeeMaker coffemaker,DishWasher washer ) {
		this.brewMaster=coffemaker;
		this.iceBox=fridge;
		this.dishwasher=washer;
	}
	
	public Refrigerator getIceBox() {
		return iceBox;
	}
	
	public DishWasher getDishwasher() {
		return dishwasher;
	}
	
	public CoffeeMaker getBrewMaster() {
		return brewMaster;
	}
	
	public void addWater() {
		brewMaster.set(true);
	}	
	public void pourMilk() {
		System.out.println("this method was called");
		iceBox.set(true);
	}
	public void loadDishWasher() {
		dishwasher.set(true);
	}
	
	public void setKitchenState(boolean value_1,boolean value_2,boolean value_3) {
		if(value_1) {
			addWater();
		}
		if(value_2) {
			pourMilk();
		}
		if(value_3) {
			loadDishWasher();
		}
	}
	public static void main(String[] args) {
		SmartKitchen kitchen=new SmartKitchen(new Refrigerator(),new CoffeeMaker(),new DishWasher());
        kitchen.pourMilk();
        kitchen.getIceBox().orderFood();
	}
}

class testing{

	
}
