package com.prana;

public class Main3 {
    public static void main(String[] args) {
        Meal meal = new Meal("Big Burger", 120.00);
        meal.addToppings("Lettuce", "Tomato", "Cheese", "Bacon", "Avocado", "Cucumber"); // Cucumber default priced
        meal.printMealDetails();
    }
}
