package storeInventorySystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
	
	private final List<InventoryItem> inventory = new ArrayList<>();
	private final List<Cart> carts = new ArrayList<>();
	private final Map<Category,List<Product>> aisleInventory = new HashMap<>();
	
	public void manageStoreCarts(Cart cart) {
        carts.add(cart);
    }
	

    public void abandonCarts(LocalDate currentDate) {
        carts.removeIf(cart -> !cart.getDate().isEqual(currentDate));
    }

    public void listProductsByCategory(Category category) {
        var categoryProducts = aisleInventory.getOrDefault(category, new ArrayList<>());
        categoryProducts.forEach(System.out::println);
    }
    
    public void checkOutCart(Cart cart) {
    	if(carts.contains(cart)) {
	        for (var entry : cart.getProducts().entrySet()) {
	            for (InventoryItem item : inventory) {
	                if (item.getProduct().equals(entry.getKey())) {
	                    item.sellItem(entry.getValue());
	                }
	            }
	        }
	        carts.remove(cart);
	        
    	}
    	else {
    		System.out.println("Not registered Cart");
    	}
    }

    public void addInventoryItem(InventoryItem item) {
        inventory.add(item);
        aisleInventory.computeIfAbsent(
        		item.getProduct().getCategory(),list -> new ArrayList<>())
        		.add(item.getProduct());
    }


	public List<Cart> getCarts() {
		return carts;
	}
    
    
}
