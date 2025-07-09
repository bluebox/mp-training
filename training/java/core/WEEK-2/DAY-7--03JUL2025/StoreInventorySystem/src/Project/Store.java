package Project;

import java.time.LocalDate;
import java.util.*;

public class Store {
    private final List<InventoryItem> inventory = new ArrayList<>();
    private final List<Cart> carts = new ArrayList<>();
    private final Map<String, List<Product>> aisleInventory = new HashMap<>();

    public void manageStoreCarts(Cart cart) {
        carts.add(cart);
    }

    public void checkOutCart(Cart cart) {
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()) {
            for (InventoryItem item : inventory) {
                if (item.getProduct().equals(entry.getKey())) {
                    item.sellItem(entry.getValue());
                }
            }
        }
        carts.remove(cart);
    }

    public void abandonCarts(LocalDate currentDate) {
        carts.removeIf(cart -> !cart.getDate().isEqual(currentDate));
    }

    public void listProductsByCategory(String category) {
        List<Product> categoryProducts = aisleInventory.getOrDefault(category, new ArrayList<>());
        categoryProducts.forEach(System.out::println);
    }

    public void addInventoryItem(InventoryItem item) {
        inventory.add(item);
        aisleInventory.computeIfAbsent(item.getProduct().getCategory(), k -> new ArrayList<>())
                      .add(item.getProduct());
    }
}

