package Day5_01_07_Burgers;

public class DeluxeBurger extends Hamburger {

    public DeluxeBurger() {
        super("Deluxe", "Chicken", 10.10, "White");
        super.addAddition("Chips", 5);
        super.addAddition("Drink", 4);
    }

    @Override
    public boolean addAddition(String name, double price) {
        System.out.println("Cannot add additional items to a deluxe burger.");
        return false;
    }
}