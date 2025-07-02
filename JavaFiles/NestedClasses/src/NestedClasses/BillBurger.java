package NestedClasses;

public class BillBurger {
	
    public class Burger {
        private String name;
        private String type;
        private double price;
        private String[] toppings;

        public Burger(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
            this.toppings = new String[0];
        }

        public void addToppings(String... toppings) {
            this.toppings = toppings;
        }

        public double calculatePrice() {
            double toppingCost = 0.0;
            for (String topping : toppings) {
                if (topping.equals("Cheese") || topping.equals("Bacon")) {
                    toppingCost += 1.0;
                }
            }
            return price + toppingCost;
        }

        public void printInfo() {
            System.out.println("Burger: " + name + " (" + type + "), Base Price: $" + price);
            System.out.print("Toppings: ");
            if (toppings.length > 0) {
                for (String topping : toppings) {
                    System.out.print(topping + " ");
                }
                System.out.println("\nTotal Price: $" + calculatePrice());
            } else {
                System.out.println("None");
                System.out.println("Total Price: $" + price);
            }
        }
    }

    public class Meal {
        private Burger burger;

        public Meal(String name, String type, double price) {
            this.burger = new Burger(name, type, price);
        }

        public void addToppingsToBurger(String... toppings) {
            burger.addToppings(toppings);
        }

        public void printMealInfo() {
            burger.printInfo();
        }
    }

    public static void main(String[] args) {
        BillBurger bill = new BillBurger();
        BillBurger.Meal meal = bill.new Meal("Classic", "Beef", 5.99);
        meal.addToppingsToBurger("Lettuce", "Tomato", "Cheese", "Bacon");
        meal.printMealInfo();
    }
}