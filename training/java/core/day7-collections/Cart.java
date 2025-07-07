package day7;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	static int counter=100;
	private int id;
	private Map<Product, Integer> items;
	private Date date;
	private CartType type;
	public Cart(int id, List<Product> products, CartType type) {
		this.id = counter++;
		this.items = new HashMap<>();
		this.date = new Date();
		this.type = type;
	}
	public Map<Product, Integer>  getItems() {
		return Collections.unmodifiableMap(items);
	}
	public void addItem(Product product,int quantity) {
		int currentQty=items.getOrDefault(product, 0);
		int newQty=currentQty+quantity;
		items.put(product, newQty);
	}
	public void removeItem(Product product,int quantity) {
		int currentQty=items.getOrDefault(product, 0);
		if(currentQty<=quantity) {
			items.remove(product);
		}
		else {
			items.put(product,currentQty-quantity);
		}
	}
}


enum CartType {
	PHYSICAL, VIRTUAL
}
