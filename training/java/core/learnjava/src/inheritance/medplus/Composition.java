package inheritance.medplus;

public class Composition {
	
	public static void main(String[] args) {
		CoffeeMaker cm = new CoffeeMaker();
		DishWasher dw = new DishWasher();
		Refrigerator rf = new Refrigerator();
		SmartKitchen sk = new SmartKitchen(cm,dw,rf);
		
		sk.addWater();
		sk.addMilk();
		sk.doKitchenWork();
		
		sk.setKitchenState(false, true, false);
		sk.doKitchenWork();
		sk.doKitchenWork();
		
		
	}
	

}
class SmartKitchen{
	private CoffeeMaker brewmaster;
	private DishWasher dishWasher;
	private Refrigerator icebox;
	
	public SmartKitchen(CoffeeMaker brewmaster,DishWasher dishWasher,Refrigerator icebox) {
		this.brewmaster = brewmaster;
		this.dishWasher=dishWasher;
		this.icebox=icebox;
	}
	
	public void addWater() {
		brewmaster.hasToDoWork = true;
	}
	
	public void addMilk() {
		icebox.hasToDoWork = true;
	}
	
	public void loadDishWasher() {
		dishWasher.hasToDoWork = true;
	}
	
	public void setKitchenState(boolean brew,boolean dish,boolean ice) {
		brewmaster.hasToDoWork = brew;
		icebox.hasToDoWork = dish;
		dishWasher.hasToDoWork = ice;
		
	}
	public void doKitchenWork() {
		brewmaster.brewCoffee();
		dishWasher.doDishes();
		icebox.orderFood();
	}
	
}

class Refrigerator{
	 boolean hasToDoWork = false;
	
	public void orderFood(){
		if (hasToDoWork) System.out.println("Ordering Food -> Ordered Food .");
		hasToDoWork = false;
	}
}

class DishWasher{
	 boolean hasToDoWork = false;
	
	public void doDishes(){
		if (hasToDoWork) System.out.println("Cleaning dishes -> Dishes are Cleaned .");
		hasToDoWork = false;
	}
}

class CoffeeMaker{
	 boolean hasToDoWork = false;
	
	public void brewCoffee(){
		if (hasToDoWork) System.out.println("Preparing coffee-> Your coffee is ready .");
		hasToDoWork = false;
	}
}

