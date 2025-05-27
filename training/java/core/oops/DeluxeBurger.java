public class DeluxeBurger extends Hamburger {
    public DeluxeBurger() {
        super("Deluxe", "Sausage & Bacon", 14.00, "White");
        super.addHamburgerAddition1("Chips", 2.0);
        super.addHamburgerAddition2("Drink", 1.0);
    }

    @Override
    public void addHamburgerAddition1(String name, double price) {
        System.out.println("\nCannot not add additional items to a deluxe burger");
    }

    @Override
    public void addHamburgerAddition2(String name, double price) {
        System.out.println("\nCannot not add additional items to a deluxe burger");
    }

    @Override
    public void addHamburgerAddition3(String name, double price) {
        System.out.println("\nCannot not add additional items to a deluxe burger");
    }

    @Override
    public void addHamburgerAddition4(String name, double price) {
        System.out.println("\nCannot not add additional items to a deluxe burger");
    }
}