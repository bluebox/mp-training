public class SmartKitchen {
    CoffeeMaker brewMaster;
    DishWasher dishWasher;
    Refrigerator iceBox;
    
    private boolean hasWorkToDo;
    public void addWater(){
        this.brewMaster.hasWorkToDo=true;
    }
    public void pourMilk(){
        this.iceBox.hasWorkToDo=true;
    }
    public void loadDishwasher(){
        this.dishWasher.hasWorkToDo=true;
    }
    public void setKitchenState(boolean a ,boolean b,boolean c){
        
        this.brewMaster.sethasWorkToDo(a);
        this.dishWasher.sethasWorkToDo(b);
        this.iceBox.sethasWorkToDo(c);
    }
     public void doKitchenWork(){
        brewMaster=new CoffeeMaker();




}
}  
