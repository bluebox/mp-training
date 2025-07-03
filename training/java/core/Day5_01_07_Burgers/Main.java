package Day5_01_07_Burgers;

public class Main {
    public static void main(String[] args) {

        Hamburger hamburger = new Hamburger("Basic", "Sausage", 5.00, "White");
        hamburger.addAddition("Tomato", 0.50);
        hamburger.addAddition("Lettuce", 0.75);
        hamburger.addAddition("Cheese", 1.13);
        System.out.println("Total Burger Price is: $" + hamburger.itemizeHamburger());
        System.out.println("--------------------------------------------------");

        HealthyBurger healthyBurger = new HealthyBurger("Turkey", 6.00);
        healthyBurger.addAddition("Egg", 1.50); 
        healthyBurger.addHealthyAddition("Lentils", 1.75);
        healthyBurger.addHealthyAddition("Avocado", 2.00);
        healthyBurger.addHealthyAddition("Carrot", 0.90); 
        System.out.println("Total Healthy Burger Price is: $" + healthyBurger.itemizeHamburger());
        System.out.println("--------------------------------------------------");

        DeluxeBurger deluxeBurger = new DeluxeBurger();
        deluxeBurger.addAddition("Should Fail", 1.00); 
        System.out.println("Total Deluxe Burger Price is: $" + deluxeBurger.itemizeHamburger());
        System.out.println("--------------------------------------------------");
    }
}
