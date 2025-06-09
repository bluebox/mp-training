package StoreInventorySystem_CollectionsFrameworkChallenge_In_Progress;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Store {
	private Map<String, InventoryItem> inventory;
	private List<Cart> carts;
	private Map<Category, List<InventoryItem>> aisleInventory;

	public Store() {
		inventory = new HashMap<>();
		carts = new ArrayList<>();
		aisleInventory = new HashMap<>();
	}

	public void addInventoryItem(InventoryItem item) {
		inventory.put(item.getProduct().getSku(), item);
		aisleInventory.computeIfAbsent(item.getProduct().getCategory(), k -> new ArrayList<>()).add(item);
	}

	public void manageOrders() {
		System.out.println("Managing the store carts...");
	}

	public void checkOutCart(Cart cart) {
		for (Entry<Product, Integer> mapEntry : cart.getProducts().entrySet()) {
			InventoryItem item = inventory.get(mapEntry.getKey().getSku());
			item.sellItem(mapEntry.getValue());
			carts.remove(cart);
			cart.printSalesSlip();
		}
	}

	public void abandonCart() {
		for (Cart cart : carts) {
			for (Entry<Product, Integer> mapEntry : cart.getProducts().entrySet()) {
				InventoryItem item = inventory.get(mapEntry.getKey().getSku());
				item.releaseItem(mapEntry.getValue());
			}
		}
		carts.clear();
	}

	public void listProductsByCategory(Category category) {
		List<InventoryItem> inventoryItems = aisleInventory.get(category);
		if (inventoryItems == null) {
			System.out.println("No products are available for the given category " + category);
			return;
		}
		for (InventoryItem item : inventoryItems) {
			System.out.println("Category: " + item.getProduct().getCategory() + " | Product: "
					+ item.getProduct().getProductName() + " | Quantity: " + item.getQtyTotal());
		}
	}

	public void addCart(Cart cart) {
		carts.add(cart);
		for (Entry<Product, Integer> mapEntry : cart.getProducts().entrySet()) {
			inventory.get(mapEntry.getKey().getSku()).reserveItem(mapEntry.getValue());
		}
	}
}
