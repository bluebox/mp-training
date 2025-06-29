package june27_constructors;

import java.util.Scanner;

public class SmartKitchenApp {

    // CoffeeMaker class
    static class CoffeeMaker {
        private boolean hasWorkToDo;

        public void setHasWorkToDo(boolean hasWorkToDo) {
            this.hasWorkToDo = hasWorkToDo;
        }

        public void brewCoffee() {
            if (hasWorkToDo) {
                System.out.println("Brewing coffee...");
                hasWorkToDo = false;
            }
        }
    }

    // Refrigerator class
    static class Refrigerator {
        private boolean hasWorkToDo;

        public void setHasWorkToDo(boolean hasWorkToDo) {
            this.hasWorkToDo = hasWorkToDo;
        }

        public void orderFood() {
            if (hasWorkToDo) {
                System.out.println("Pouring milk...");
                hasWorkToDo = false;
            }
        }
    }

    // DishWasher class
    static class DishWasher {
        private boolean hasWorkToDo;

        public void setHasWorkToDo(boolean hasWorkToDo) {
            this.hasWorkToDo = hasWorkToDo;
        }

        public void doDishes() {
            if (hasWorkToDo) {
                System.out.println("Washing dishes...");
                hasWorkToDo = false;
            }
        }
    }

    // SmartKitchen class
    static class SmartKitchen {
        private CoffeeMaker brewMaster;
        private Refrigerator iceBox;
        private DishWasher dishWasher;

        public SmartKitchen() {
            brewMaster = new CoffeeMaker();
            iceBox = new Refrigerator();
            dishWasher = new DishWasher();
        }

        public CoffeeMaker getBrewMaster() {
            return brewMaster;
        }

        public Refrigerator getIceBox() {
            return iceBox;
        }

        public DishWasher getDishWasher() {
            return dishWasher;
        }

        // Set individual tasks
        public void addWater() {
            brewMaster.setHasWorkToDo(true);
        }

        public void pourMilk() {
            iceBox.setHasWorkToDo(true);
        }

        public void loadDishwasher() {
            dishWasher.setHasWorkToDo(true);
        }

        // Set all states together
        public void setKitchenState(boolean coffee, boolean milk, boolean dishes) {
            brewMaster.setHasWorkToDo(coffee);
            iceBox.setHasWorkToDo(milk);
            dishWasher.setHasWorkToDo(dishes);
        }

        // Do all kitchen work
        public void doKitchenWork() {
            brewMaster.brewCoffee();
            iceBox.orderFood();
            dishWasher.doDishes();
        }
    }

    // Main method for dynamic execution
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SmartKitchen kitchen = new SmartKitchen();

        System.out.println("Do you want to brew coffee? (true/false)");
        boolean coffee = sc.nextBoolean();

        System.out.println("Do you want to pour milk? (true/false)");
        boolean milk = sc.nextBoolean();

        System.out.println("Do you want to do dishes? (true/false)");
        boolean dishes = sc.nextBoolean();

        // Setting kitchen tasks
        kitchen.setKitchenState(coffee, milk, dishes);

        // Execute all tasks
        System.out.println("\n--- Performing Kitchen Work ---");
        kitchen.doKitchenWork();

        sc.close();
    }
}
