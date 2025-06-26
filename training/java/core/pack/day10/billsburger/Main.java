package day10.billsburger;

public class Main {

	public static void main(String[] args) {
		 Hamburger hamburger = new Hamburger("Basic", "Sausage", 3.56, "White");
		hamburger.addHamburgerAddition1("Tomato", 0.27);
		hamburger.addHamburgerAddition2("Lettuce", 0.75);
		hamburger.addHamburgerAddition3("Cheese", 1.13);
		System.out.println("Total Burger price is " + hamburger.itemizeHamburger());

		//healthy burger is  overriding the hamburger behaviours and acessing the updated fileds and behaviour through healthy burger object
		HealthyBurger healthyBurger = new HealthyBurger("Bacon", 5.67);
		healthyBurger.addHamburgerAddition1("Egg", 5.43);
		healthyBurger.addHealthyAddition1("Lentils", 3.41);
		System.out.println("Total Healthy Burger price is  " + healthyBurger.itemizeHamburger());

		//deluxe burger is setting parents state and behaviour through child constructor(using super)and calling the parent data through 
		//through deluxe burger object because this object has acess to parent class and methods
		Hamburger db = new DeluxeBurger();
		//DeluxeBurger db=new DeluxeBurger();
		db.addHamburgerAddition3("Should not do this", 50.53);
		System.out.println("Total Deluxe Burger price is " + db.itemizeHamburger());

	}

}
