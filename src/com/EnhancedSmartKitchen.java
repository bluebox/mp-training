package com;

import java.util.ArrayList;
import java.util.List;

public class EnhancedSmartKitchen {

    private List<Appliance> appliances;

    public EnhancedSmartKitchen(Refrigerator refrigerator, CoffeeMaker coffeeMaker, DishWasher dishWasher) {
        appliances = new ArrayList<>();
        appliances.add(refrigerator);
        appliances.add(coffeeMaker);
        appliances.add(dishWasher);
    }

    public void setKitchenState(boolean setRefrigerator, boolean setCoffeeMaker, boolean setDishWasher) {
        appliances.get(0).setState(setRefrigerator); // refrigerator
        appliances.get(1).setState(setCoffeeMaker);  // coffee maker
        appliances.get(2).setState(setDishWasher);   // dishwasher
    }

    public static void getKitchenState(EnhancedSmartKitchen smartKitchen) {
        System.out.println("The running appliances in the Enhanced smart kitchen are...");
        for (Appliance appliance : smartKitchen.appliances) {
            if (appliance.getState()) {
                System.out.println(appliance.getClass().getSimpleName());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        EnhancedSmartKitchen smartKitchen = new EnhancedSmartKitchen(
            new Refrigerator(), new CoffeeMaker(), new DishWasher()
        );
        smartKitchen.setKitchenState(true, false, true);
        EnhancedSmartKitchen.getKitchenState(smartKitchen);
    }
}

