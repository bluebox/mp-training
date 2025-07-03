package Project;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final String id;
    private final LocalDate date;
    private final CartType type;
    private final Map<Product, Integer> products = new HashMap<>();

    public Cart(String id, LocalDate date, CartType type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    public void addItem(Product product, int qty) {
        products.put(product, products.getOrDefault(product, 0) + qty);
    }

    public void removeItem(Product product) {
        products.remove(product);
    }

    public void printSalesSlip() {
        System.out.println("Cart: " + id + " (" + type + ")");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            System.out.println("- " + entry.getKey() + " x" + entry.getValue());
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public CartType getType() {
        return type;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }
}

