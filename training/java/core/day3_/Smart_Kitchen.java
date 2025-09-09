package com.day3_;
public class Smart_Kitchen {
    private CoffeeMaker cofee;
    private Refrigerator fridge;
    private DishWasher washer;
    public Smart_Kitchen() {
        cofee = new CoffeeMaker();
        fridge = new Refrigerator();
        washer = new DishWasher();
    }
    public CoffeeMaker getCoffee() {
        return cofee;
    }
    public Refrigerator getFridge() {
        return fridge;
    }
    public DishWasher getWasher() {
        return washer;
    }
    public void setKitchenState(boolean coffeeFlag, boolean fridgeFlag,
                                boolean dishWasherFlag) {
        cofee.setHasWorkToDo(coffeeFlag);
        fridge.setHasWorkToDo(fridgeFlag);
        washer.setHasWorkToDo(dishWasherFlag);
    }
    public void doKitchenWork() {
        cofee.brewCoffee();
        fridge.orderFood();
        washer.doDishes();
    }
}
class CoffeeMaker {

    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }
    public void brewCoffee() {
        if (hasWorkToDo) {
            System.out.println("Brewing hot Coffee");
            hasWorkToDo = false;
        }
    }
}


class Refrigerator {

    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void orderFood() {

        if (hasWorkToDo) {
            System.out.println("Ordering the Food");
            hasWorkToDo = false;
        }
    }
}

class DishWasher {

    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void doDishes() {

        if (hasWorkToDo) {
            System.out.println("Washing Dishes now");
            hasWorkToDo = false;
        }
    }
}