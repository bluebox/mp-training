package StoreInventry;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
	private List<InventoryItem> inventory = new ArrayList<>();
    private List<Cart> carts = new ArrayList<>();
    private Map<String, List<InventoryItem>> aisleInventory = new HashMap<>();

    public void addInventoryItem(InventoryItem item) {
        inventory.add(item);
        aisleInventory.computeIfAbsent(item.getProduct().getCategory(), k -> new ArrayList<>()).add(item);
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    public void listProductsByCategory(String category) {
        System.out.println("Products in category: " + category);
        List<InventoryItem> items = aisleInventory.get(category);
        if (items != null) {
            for (InventoryItem i : items) {
                System.out.println("- " + i.getProduct().getName() + ": " + i.getQtyTotal());
            }
        } else {
            System.out.println("No items found in this category.");
        }
    }

    public void abandonCarts(Date currentDate) {
        carts.removeIf(cart -> !cart.getDate().equals(currentDate));
    }
}
