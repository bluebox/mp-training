package corejava.july1_InnerClass;

public class BillsBurgers {
    public static void main(String[] args) {
        Meal meal = new Meal("My Burger", 1100);
        meal.addToppings("Lettuce", "Tomato", "Cheese", "Bacon", "Pickles");
        meal.printMeal();
    }
}
