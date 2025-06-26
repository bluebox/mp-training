package OOPS;
public class BillsBurgers {
    public static void main(String[] args) {
        Meal regularMeal = new Meal();
        regularMeal.prepareMeal();

        Meal.SpecialMeal special = regularMeal.new SpecialMeal();
        special.addSpecialTopping();

        double total = regularMeal.getPrice() + special.getToppingPrice();
        System.out.println("Total Bill: $" + total);
    }
}

class Meal {
    private String burger = "Regular Burger";
    private String drink = "Cola";
    private double burgerPrice = 5.99;
    private double drinkPrice = 1.99;

    public void prepareMeal() {
        System.out.println("Preparing " + burger + " with " + drink);
    }

    public double getPrice() {
        return burgerPrice + drinkPrice;
    }

    class SpecialMeal {
        private String topping = "Cheese & Bacon";
        private double toppingPrice = 2.50;

        public void addSpecialTopping() {
            System.out.println("Adding " + topping + " to " + burger);
        }

        public double getToppingPrice() {
            return toppingPrice;
        }
    }
}
