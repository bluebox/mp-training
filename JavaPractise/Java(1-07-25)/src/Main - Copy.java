
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        Meal meal = new Meal();

		        meal.addBurger("Classic", 150.0, "lettuce", "tomato", "cheese", "bacon");
		        meal.addBurger("Veggie", 130.0, "lettuce", "tomato", "onion", "avocado");

		        meal.printMeal();
	}

}
