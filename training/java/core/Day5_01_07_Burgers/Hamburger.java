package Day5_01_07_Burgers;

import java.util.ArrayList;
import java.util.List;

public class Hamburger {
    private String name;
    private String meat;
    private double price;
    private String breadRollType;


    private static class Addition {
        String name;
        double price;
        Addition(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private final List<Addition> additions = new ArrayList<>();
    private final int maxAdditions = 4;

    public Hamburger(String name, String meat, double price, String breadRollType) {
        this.name = name;
        this.meat = meat;
        this.price = price;
        this.breadRollType = breadRollType;
    }

    public boolean addAddition(String name, double price) {
        if (additions.size() >= maxAdditions) {
            System.out.println("Cannot add more than " + maxAdditions + " additions.");
            return false;
        }
        additions.add(new Addition(name, price));
        return true;
    }

    public double itemizeHamburger() {
        double totalPrice = this.price;
        System.out.println(this.name + " hamburger on a " + this.breadRollType +
                " roll with " + this.meat + ", price is " + this.price);
        for (Addition addition : additions) {
            System.out.println("Added " + addition.name + " for an extra " + addition.price);
            totalPrice += addition.price;
        }
        return totalPrice;
    }
}
