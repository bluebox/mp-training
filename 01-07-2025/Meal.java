package com.prana;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Meal {
    private Burger burger;

    public Meal(String burgerName, double burgerPrice) {
        this.burger = new Burger(burgerName, burgerPrice);
    }

    public void addToppings(String... toppings) {
        burger.addToppings(toppings);
    }

    public void printMealDetails() {
        burger.printBurgerDetails();
    }

    public class Burger extends Item {
        private List<Item> toppings;

        private final Map<String, Double> toppingPrices = Map.of(
                "Lettuce", 0.0,
                "Tomato", 0.0,
                "Cheese", 15.0,
                "Bacon", 30.0,
                "Onion", 0.0,
                "Avocado", 25.0,
                "Egg", 20.0
        );

        public Burger(String name, double price) {
            super(name, "Burger", price);
            toppings = new ArrayList<>();
        }

        public void addToppings(String... toppingNames) {
            for (String toppingName : toppingNames) {
                double price = toppingPrices.getOrDefault(toppingName, 10.0); // default price
                toppings.add(new Item(toppingName, "Topping", price));
            }
        }

        public void printBurgerDetails() {
            System.out.println(super.toString());

            double toppingsTotal = 0;
            if (!toppings.isEmpty()) {
                System.out.println("Toppings:");
                for (Item topping : toppings) {
                    System.out.printf("- %s: %s\n", topping.getName(), topping.getPriceString());
                    toppingsTotal += topping.getPrice();
                }
            }

            double totalPrice = getPrice() + toppingsTotal;
            System.out.printf("Total Price: ₹%.2f\n", totalPrice);
        }
    }
}
