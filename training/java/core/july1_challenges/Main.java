package july_1;

public class Main {
    public static void main(String[] args) {
        Meal meal = new Meal("Classic Beef Burger", 5.99);
        meal.addToppingsToBurger("Lettuce", "Cheese", "Bacon", "Onion", "Pickles");
        meal.showMealDetails();
    }
}
