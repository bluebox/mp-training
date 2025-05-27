
public class Main {

	public static void main(String[] args) {
			Hamburger hamburger = new Hamburger("Basic", "Sausage", 3.00, "White");
	        hamburger.addHamburgerAddition1("Tomato", 1.00);
	        hamburger.addHamburgerAddition2("Lettuce", 2.00);
	        hamburger.addHamburgerAddition3("Cheese", 3.00);
	        System.out.println("Total Burger price is " + hamburger.itemizeHamburger()+"\n");

	        HealthyBurger healthyBurger = new HealthyBurger("Bacon", 7.00);
	        healthyBurger.addHamburgerAddition1("Egg", 2.00);
	        healthyBurger.addHealthAddition1("Lentils", 1.00);
	        System.out.println("Total Healthy Burger price is  " + healthyBurger.itemizeHamburger()+"\n");

	        DeluxeBurger deluxeBurger = new DeluxeBurger();
	        //deluxeBurger.addHamburgerAddition3("Should not do this", 50.53);
	        deluxeBurger.itemizeHamburger();

	}

}
