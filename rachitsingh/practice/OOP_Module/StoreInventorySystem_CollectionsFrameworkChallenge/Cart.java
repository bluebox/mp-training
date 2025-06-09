package StoreInventorySystem_CollectionsFrameworkChallenge_In_Progress;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private final String ID;
	private final LocalDateTime date;
	private final String type;
	private final Map<Product, Integer> products;

	public Cart(String ID, String type) {
		this.ID = ID;
		this.type = type;
		this.date = LocalDateTime.now();
		this.products = new HashMap<>();
	}

	public void addItem(Product product, Integer quantity) {
		products.putIfAbsent(product, quantity);
	}

	public void removeItem(Product product) {
		products.remove(product);
	}

	public void printSalesSlip() {
		System.out.println("Sales Slip - Cart ID: " + ID);
		for (Map.Entry<Product, Integer> mapEntry : products.entrySet()) {

			System.out.println(mapEntry.getKey().getProductName() + " - Quantity: " + mapEntry.getValue());
		}
	}
	
	public Map<Product, Integer> getProducts(){
		return products;
	}
	
	public String getID() {
		return ID;
	}
}
