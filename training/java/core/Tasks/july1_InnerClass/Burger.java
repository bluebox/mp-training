package corejava.july1_InnerClass;

import java.util.ArrayList;
import java.util.List;

class Burger extends Item {
    private List<Item> toppings = new ArrayList<>();

    public Burger(String name, double price) {
        super(name, "Burger", price);
    }

    public void addTopping(String name) {
        double extraCost = switch (name.toLowerCase()) {
            case "cheese", "bacon" -> 20.0;
            case "egg" -> 15.0;
            default -> 0.0;
        };
        toppings.add(new Topping(name, "Topping", extraCost));
    }

    @Override
    public void printItem() {
        System.out.println("Burger: " + getName() + " - " + getPriceFormatted());
        if (toppings.isEmpty()) {
            System.out.println("No toppings added.");
        } else {
            System.out.println("Toppings:");
            for (Item topping : toppings) {
                System.out.println("  - " + topping.getName() + ": " + topping.getPriceFormatted());
            }
        }

        double total = getPrice();
        for (Item topping : toppings) {
            total += topping.getPrice();
        }
        System.out.printf("Total Price: ₹%.2f\n", total);
    }

    private class Topping extends Item {
        public Topping(String name, String type, double price) {
            super(name, type, price);
        }

        @Override
        public void printItem() {
            System.out.println(getName() + " - " + getPriceFormatted());
        }
    }
}
