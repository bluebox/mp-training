package training.java.core.DAY2;

public class CompositionChallenge {
    public static void main(String[] args) {
        SmartKitchen sk= new SmartKitchen();

       
    }
}
class SmartKitchen{
     private CoffeeMaker cm;
    private DishWasher dw;
    private Refrigerator rf;
    public SmartKitchen(){
        cm= new CoffeeMaker();
        dw= new DishWasher();
        rf = new Refrigerator();
    }
    
    public CoffeeMaker getCm() {
        return cm;
    }

    public DishWasher getDw() {
        return dw;
    }

    public Refrigerator getRf() {
        return rf;
    }

    public void addWater(){
        cm.hasWorkToDo=true;
    }
    public void pourMilk(){
        rf.hasWorkToDo=true;
    }
    public void loadDishWasher(){
        dw.hasWorkToDo=true;
    }
    public void setKitchenState(boolean a,boolean b,boolean c){
        cm.hasWorkToDo=a;
        rf.hasWorkToDo=b;
        dw.hasWorkToDo=c;
    }
    public void doKitchenWork(){
        cm.brewCoffee();
        rf.orderFood();
        dw.doDishes();
    }
}
class CoffeeMaker{
    boolean hasWorkToDo;
    public void brewCoffee(){
        if(hasWorkToDo){
            System.out.println("coffee class working");
        }else{
            System.out.println("coffee class not working");
        }
    }
}
class  DishWasher {
    boolean hasWorkToDo;
    public void doDishes(){
        if(hasWorkToDo){
            System.out.println("dish class working");
        }else{
            System.out.println("dish class not working");
        }
    }
    
}
class Refrigerator{
    boolean hasWorkToDo;
    public void orderFood(){
        if(hasWorkToDo){
            System.out.println("ref class working");
        }else{
            System.out.println("ref class not working");
        }
    }
}