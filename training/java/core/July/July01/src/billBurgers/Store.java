package billBurgers;

public class Store {

    public static void main(String[] args) {

        Meal regularMeal = new Meal();
        regularMeal.addToppings("cheese", "Avocado", "Bacon", "milk");
        System.out.println(regularMeal);

        Meal regularMeal1 = new Meal(0.75);
        System.out.println(regularMeal1);

    }
}


