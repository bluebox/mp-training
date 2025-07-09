package day7;

import java.util.HashSet;
import java.util.Set;

public class Store {
	private Set<InventoryItem> inventory;
	private Set<Cart> carts;

	public Store() {
		this.inventory = new HashSet<>();
		this.carts = new HashSet<>();
	}

	public Set<InventoryItem> getInventory() {
		return inventory;
	}

	public void setInventory(Set<InventoryItem> inventory) {
		this.inventory = inventory;
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	public void stockStore() {
		addProduct(new Product("Laptop", "Asus", Category.ELECTRONICS), 50, 25, 10, 30000);
		addProduct(new Product("Sofa", "3-seater", Category.FURNTITURE), 40, 20, 15, 40000);
	}

	public void addProduct(Product product, int qtyTotal, int qtyReorder, int qtyLow, int price) {
		InventoryItem item = new InventoryItem(product, qtyTotal, qtyReorder, qtyLow, price);
		inventory.add(item);
	}

}
