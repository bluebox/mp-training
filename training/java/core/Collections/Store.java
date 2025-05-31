package Collections;

import java.util.ArrayList;

abstract class ProductForSale {
    protected String type;
    protected double price;
    protected String description;

    public ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public double getSalesPrice(int qty) {
        return price * qty;
    }

    public void printPricedItem(int qty) {
        System.out.printf("%d x %s = $%.2f%n", qty, type, getSalesPrice(qty));
    }

    public abstract void showDetails();
}

class ProductA extends ProductForSale {
    public ProductA(String type, double price, String description) {
        super(type, price, description);
    }

    public void showDetails() {
        System.out.println("ProductA - " + type + ": " + description + " @ $" + price);
    }
}

class ProductB extends ProductForSale {
    public ProductB(String type, double price, String description) {
        super(type, price, description);
    }

    public void showDetails() {
        System.out.println("ProductB - " + type + ": " + description + " @ $" + price);
    }
}

class ProductC extends ProductForSale {
    public ProductC(String type, double price, String description) {
        super(type, price, description);
    }

    public void showDetails() {
        System.out.println("ProductC - " + type + ": " + description + " @ $" + price);
    }
}

record OrderItem(int qty, ProductForSale product) { }

public class Store {
    private ArrayList<ProductForSale> products = new ArrayList<>();
    private ArrayList<OrderItem> order = new ArrayList<>();

    public void addItemToOrder(int index, int qty) {
        if (index >= 0 && index < products.size()) {
            ProductForSale item = products.get(index);
            order.add(new OrderItem(qty, item));
        }
    }

    public void printOrder() {
        double total = 0.0;
        for (OrderItem item : order) {
            item.product().printPricedItem(item.qty());
            total += item.product().getSalesPrice(item.qty());
        }
        System.out.printf("Total Order Amount = $%.2f%n", total);
    }

    public static void main(String[] args) {
        Store store = new Store();

        store.products.add(new ProductA("Notebook", 5.99, "A ruled paper notebook"));
        store.products.add(new ProductB("Pen", 1.49, "A smooth writing pen"));
        store.products.add(new ProductC("Backpack", 29.99, "A sturdy backpack for school"));

        System.out.println("Product List:");
        for (ProductForSale p : store.products) {
            p.showDetails();
        }

        store.addItemToOrder(0, 3);
        store.addItemToOrder(1, 5);
        store.addItemToOrder(2, 1);

        System.out.println("\nOrder Receipt:");
        store.printOrder();
    }
}

