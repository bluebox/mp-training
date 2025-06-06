package SmartKitchen;

public class SmartKitchen {
	public CoffeeMaker brewMaster;
	public DishWasher dishWasher;
	public Refrigerator iceBox;
	
	
	public SmartKitchen(CoffeeMaker brewMaster,DishWasher dishWasher, Refrigerator iceBox) {
		this.brewMaster=brewMaster;
		this.dishWasher=dishWasher;
		this.iceBox=iceBox;
		
	}
	public void addWater() {
		this.iceBox.hasWorkToDo=true;
	}
	
	public void pourMilk() {
		this.brewMaster.hasWorkToDo=true;
	}
	
	public void loadDishwasher() {
		this.dishWasher.hasWorkToDo=true;
	}
	
	
	public void setKitchenState(boolean rf, boolean cm, boolean dw) {
		brewMaster.hasWorkToDo=cm;
		iceBox.hasWorkToDo=rf;
		dishWasher.hasWorkToDo=dw;
	}
	public void doKitchenWork() {
		brewMaster.brewCoffee();
		dishWasher.doDishes();
		iceBox.orderFood();
	}
	
	public static void main(String[] args) {
		Refrigerator rfg=new Refrigerator();
		DishWasher dws =new DishWasher();
		CoffeeMaker cbm= new CoffeeMaker();
		SmartKitchen sk=new SmartKitchen(cbm,dws,rfg);
		sk.setKitchenState(false, false, false);
		//sk.addWater();
		//sk.pourMilk();
		//sk.loadDishwasher();
		sk.doKitchenWork();
		sk.addWater();
		sk.doKitchenWork();
		sk.pourMilk();
		sk.doKitchenWork();
		sk.loadDishwasher();
		
		
	}
}

class Refrigerator{
	public boolean hasWorkToDo;
	public void orderFood() {
		if(hasWorkToDo) {
			System.out.println("Ordering Food");
			this.hasWorkToDo=false;
		}else {
			System.out.println("No Food to order");
		}
	}
}

class DishWasher{
	public boolean hasWorkToDo;
	public void doDishes() {
		if(hasWorkToDo) {
			System.out.println("Washing Dishes");
			this.hasWorkToDo=false;
		}else {
			System.out.println("No Dishes to wash");
		}
	}
}
class CoffeeMaker{
	public boolean hasWorkToDo;
	public void brewCoffee() {
		if(hasWorkToDo) {
			System.out.println("Brewing Coffee");
			this.hasWorkToDo=false;
		}else {
			System.out.println("No milk to make coffee");
		}
	}
}
