package storesinven;

import java.util.Date;
import java.util.HashMap;

public class Cart {
    private HashMap<Product, Integer> products = new HashMap<>();
    private Date date;

    public Cart(Date date) {
        this.date = date;
    }

    public boolean addItem(Product product, int qty, InventoryItem inventory) {
        if (inventory.reduceQuantity(qty)) {
            products.put(product, products.getOrDefault(product, 0) + qty);
            return true;
        }
        return false;
    }

    public boolean removeItem(Product product, int qty, InventoryItem inventory) {
        if (products.containsKey(product)) {
            int currentQty = products.get(product);
            if (qty <= currentQty) {
                products.put(product, currentQty - qty);
                if (products.get(product) == 0) products.remove(product);
                inventory.addQuantity(qty);
                return true;
            }
        }
        return false;
    }

    public void printCart() {
        System.out.println("Cart Date: " + date);
        if (products.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }
        for (Product p : products.keySet()) {
            System.out.println(p.getName() + ": " + products.get(p));
        }
    }
}

