package pack;

public class Composition {
	
public static void main(String args[]) {
	Refrigrator r=new Refrigrator();
	DishMaker d=new DishMaker();
	CoffeMaker c=new CoffeMaker();
	
	SmartKitchen sk=new SmartKitchen(r,d,c);
	sk.setKitchenState(true,false,false);
	sk.setKitchenState(false, true, true);
}

} //ssh-keygen -t ed25519 -C "pinnurisaikrishna@gmail.com"

class SmartKitchen{
   private	Refrigrator r;
   private DishMaker d;
   private CoffeMaker c;
   
   SmartKitchen(Refrigrator r,DishMaker d,CoffeMaker c){
	   this.r=r;
	   this.d=d;
	   this.c=c;
   }
   public void  addWater() {
	   r.orderFood();
   }
   public void  pourMilk() {
	   c.brewCoffe();
   }
   public void  loadDishWater() {
	   d.doDishes();
   }
   public void setKitchenState(boolean waterToAdded,boolean milkToPoured,boolean dishesToDone) {
	   
	   if(waterToAdded) {
		   r.hasWorkToDo=true;
		   addWater();
	   }
	   if(dishesToDone) {
		   d.hasWorkToDo=true;
		   loadDishWater();
	   }
	   if(milkToPoured) {
		   c.hasWorkToDo=true;
		   pourMilk();
	   }
	   
	   
   }
//   public doKitchenWork() {
//	   
//   }
	
}
class Refrigrator{
	boolean hasWorkToDo;
	public void orderFood() {
		if(hasWorkToDo) {
			System.out.println("Food is ordered over refrigrator");
			hasWorkToDo=false;
		}
	}
}
class DishMaker{
	boolean hasWorkToDo;
	public void  doDishes() {
		if(hasWorkToDo) {
			System.out.println("Dishes are washed over Dishmaker");
			hasWorkToDo=false;
		}
	}
}
class CoffeMaker{
	boolean hasWorkToDo;
	public void  brewCoffe() {
		if(hasWorkToDo) {
			System.out.println("Food is ordered over refrigrator");
			hasWorkToDo=false;
		}
	}
}