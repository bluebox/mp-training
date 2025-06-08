package composition;


class Refrigerator {
 private boolean hasWorkToDo;

 public Refrigerator(boolean hasWorkToDo) {
     this.hasWorkToDo = hasWorkToDo;
 }

 public void orderFood() {
     if (hasWorkToDo) {
         System.out.println("refrigerator");
         hasWorkToDo = false;
     }
 }

 public boolean isHasWorkToDo() {
     return hasWorkToDo;
 }

 public void setHasWorkToDo(boolean hasWorkToDo) {
     this.hasWorkToDo = hasWorkToDo;
 }
}


class DishWasher {
 private boolean hasWorkToDo;

 public DishWasher(boolean hasWorkToDo) {
     this.hasWorkToDo = hasWorkToDo;
 }

 public void doDishes() {
     if (hasWorkToDo) {
         System.out.println("DishWasher");
         hasWorkToDo = false; 
     }
 }

 public boolean isHasWorkToDo() {
     return hasWorkToDo;
 }

 public void setHasWorkToDo(boolean hasWorkToDo) {
     this.hasWorkToDo = hasWorkToDo;
 }
}


class CoffeeMaker {
 private boolean hasWorkToDo;

 public CoffeeMaker(boolean hasWorkToDo) {
     this.hasWorkToDo = hasWorkToDo;
 }

 public void brewCoffee() {
     if (hasWorkToDo) {
         System.out.println("Brewing coffee...");
         hasWorkToDo = false; 
     }
 }

 public boolean isHasWorkToDo() {
     return hasWorkToDo;
 }

 public void setHasWorkToDo(boolean hasWorkToDo) {
     this.hasWorkToDo = hasWorkToDo;
 }
}


class SmartKitchen {
 private CoffeeMaker brewMaster;
 private DishWasher dishWasher;
 private Refrigerator iceBox;

 public SmartKitchen() {
     this.brewMaster = new CoffeeMaker(false);
     this.dishWasher = new DishWasher(false);
     this.iceBox = new Refrigerator(false);
 }


 public CoffeeMaker getBrewMaster() {
     return brewMaster;
 }

 public DishWasher getDishWasher() {
     return dishWasher;
 }

 public Refrigerator getIceBox() {
     return iceBox;
 }

 public void addWater() {
     brewMaster.setHasWorkToDo(true);
     System.out.println("Water added");
 }

 public void pourMilk() {
     iceBox.setHasWorkToDo(true); 
     System.out.println("Milk poured");
 }

 public void loadDishwasher() {
     dishWasher.setHasWorkToDo(true);
     System.out.println("Dishwasher loaded.");
 }

 
 public void setKitchenState(boolean coffeeFlag, boolean fridgeFlag, boolean dishWasherFlag) {
     brewMaster.setHasWorkToDo(coffeeFlag);
     iceBox.setHasWorkToDo(fridgeFlag);
     dishWasher.setHasWorkToDo(dishWasherFlag);
 }

 public void doKitchenWork() {
     System.out.println("starting");
     brewMaster.brewCoffee();
     dishWasher.doDishes();
     iceBox.orderFood();
     System.out.println("end");
 }
}


public class Main12 {
	
 public static void main(String... args) {
	 SmartKitchen myKitchen = new SmartKitchen();

	 
     myKitchen.doKitchenWork();

     
     myKitchen.addWater(); 
     myKitchen.pourMilk();
     myKitchen.doKitchenWork(); 

     
     myKitchen.loadDishwasher();
     myKitchen.setKitchenState(false, true, true); 
     myKitchen.doKitchenWork(); 

     
 }
}
