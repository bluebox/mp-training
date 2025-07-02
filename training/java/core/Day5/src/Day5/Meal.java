package Day5;

import java.util.ArrayList;
import java.util.List;



public class Meal {

    private Burger burger;

    public Meal(String name, double basePrice) {
        this.burger = new Burger(name, basePrice);
    }

    public void addToppings(String... toppingNames) {
        for (String topping : toppingNames) {
            burger.addTopping(topping);
        }
    }

    public void printMeal() {
        burger.printItem();
    }

    private class Burger extends Item {
        private List<Item> toppings = new ArrayList<>();
        private double basePrice;
        public Burger(String name,double price) {
            super(name, "Burger" , price);
            this.basePrice=price;
        }

        public void addTopping(String toppingName) {
            double price =0;
            switch (toppingName.toLowerCase()) {
                case "cheese" :price= 1.00;break;
                case "bacon" :price =1.50;break;
                case "avocado" :price= 2.00;break;
                case "letuce" :price=0.50;break;
                case "sauce":price=1.00;break;
                default : price= 0.00; 
            }
            toppings.add(new Item(toppingName, "Topping", price));
        }

        @Override
        public void printItem() {
        	double totalPrice=basePrice;
            super.printItem();
            System.out.println("Toppings:");
            for (Item topping : toppings) {
                topping.printItem();
                totalPrice+=topping.getPrice();
            }
            System.out.println("Total cost of burger : "+totalPrice);
        }
    }
}

class Item {
    private String name;
    private String type;
    private double price;

    public Item(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public void printItem() {
        System.out.printf("%s (%s): $%.2f%n", name, type, price);
    }

    public double getPrice() {
        return price;
    }

    public double getPriceInCents() {
        return price * 100;
    }
}