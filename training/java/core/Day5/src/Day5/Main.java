package Day5;

public class Main {
	public static void main(String[] args) {
		Meal meal=new Meal("Classic Burger",150);
		meal.addToppings("cheese", "bacon", "beans", "butter", "bacon","sauce");
		meal.printMeal();
	}
}
