package july_1;

import java.util.ArrayList;
import java.util.List;

public class Burger extends Item {
    private List<Topping> toppings;

    public Burger(String name, double basePrice) {
        super(name, "Burger", basePrice);
        this.toppings = new ArrayList<>();
        this.name
        
    }

    public void addToppings(String... toppingNames) {
        for (String name : toppingNames) {
            switch (name.toLowerCase()) {
                case "lettuce", "onion" -> toppings.add(new Topping(name, 0.0));
                case "cheese" -> toppings.add(new Topping(name, 0.50));
                case "bacon" -> toppings.add(new Topping(name, 1.00));
                default -> System.out.println("⚠️ Unknown topping: " + name + " (skipped)");
            }
        }
    }

    public double getTotalPrice() {
        double total = price;
        for (Topping t : toppings) {
            total += t.price;
        }
        return total;
    }

    public void showDetails() {
        System.out.println("🍔 Burger: " + name);
        System.out.println("Base Price: $" + String.format("%.2f", price));
        if (toppings.isEmpty()) {
            System.out.println("No toppings added.");
        } else {
            System.out.println("Toppings:");
            for (Topping t : toppings) {
                System.out.println(" - " + t.name + ": $" + String.format("%.2f", t.price));
            }
        }
        System.out.println("Total Burger Price: $" + String.format("%.2f", getTotalPrice()));
    }
}
