package OOPS;

class Refrigerator{
	boolean hasWorkToDo;
	public void orderFood() {
		System.out.println("Refrigerator - Food ordered");
		hasWorkToDo=false;
	}
}
class DishWasher{
	boolean hasWorkToDo;
	public void doDishes() {
		System.out.println("DishWasher - Washing is done");
		hasWorkToDo=false;
	}
}
class CoffeeMaker{
	boolean hasWorkToDo;
	public void brewCoffee() {
		System.out.println("CoffeeMaker - Coffee is Readyy");
		hasWorkToDo=false;
	}
}

public class CompositionSmartKitchen {
	private CoffeeMaker brewMaster;
	private Refrigerator iceBox;
	private DishWasher dishWash;
	public CompositionSmartKitchen(CoffeeMaker brewMaster,Refrigerator iceBox,DishWasher dishWash) {
		this.brewMaster=brewMaster;
		this.iceBox=iceBox;
		this.dishWash=dishWash;
	}
	public static void main(String[] args) {
		CoffeeMaker cm=new CoffeeMaker();
		Refrigerator rf= new Refrigerator();
		DishWasher dw=new DishWasher();
		CompositionSmartKitchen sk=new CompositionSmartKitchen(cm,rf,dw);
		sk.setKitchenState(true, true, false);
	}
	public void addWater() {
		brewMaster.hasWorkToDo=true;
	}
	public void pourMilk() {
		iceBox.hasWorkToDo=true;
	}
	public void loadDishWasher() {
		dishWash.hasWorkToDo=true;
	}
	public void setKitchenState(boolean refrigerator,boolean coffee,boolean dish) {
		if(refrigerator) {
			pourMilk();
			iceBox.orderFood();
		}
		if(coffee) {
			addWater();
			brewMaster.brewCoffee();
			
		}
		if(dish) {
			loadDishWasher();
			dishWash.doDishes();
		}
		
	}
}


