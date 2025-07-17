package july_1;

public class Meal {
    private Burger burger;

    public Meal(String burgerName, double basePrice) {
        this.burger = new Burger(burgerName, basePrice);
    }

    public void addToppingsToBurger(String... toppings) {
        burger.addToppings(toppings);
    }

    public void showMealDetails() {
        burger.showDetails();
    }
}
