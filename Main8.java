public class Main8 {
    public static void main(String[] args) {
        SmartKitchen kitchen = new SmartKitchen();

        
        kitchen.addWater();        
        kitchen.loadDishwasher();

        
        //kitchen.setKitchenState(true, true, false);

        
        kitchen.doKitchenWork();
    }
}
