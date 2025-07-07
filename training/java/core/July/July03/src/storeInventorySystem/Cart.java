package storeInventorySystem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	private String id;
	private Map<Product,Integer> products;
	private LocalDate date;
	private CartType type;
	
	public Cart(String id, Product products, LocalDate date, CartType type) {
		
		this.id = id;
		this.products = new HashMap<>();
		if(products != null) {
			this.products.put(products, 0);
		}
		this.date = date;
		this.type = type;
		
	}
	
	public Cart(String id,LocalDate date, CartType type) {
		this(id,null,date,type);
	}
	
	public void addItem(Product product, int qty) {
		this.products.put(product,products.getOrDefault(product, 0)+qty);
	}
	
	public void removeItem(Product product) {
		this.products.remove(product);
	}
	
	public void printSaleSlip() {
		System.out.println("Cart: " + id + " [" + type + "]");
		System.out.println("Product : 	Qty");
        for (var entry : products.entrySet()) {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue());
        }
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<Product, Integer> getProducts() {
		return new HashMap<>(products);
	}

	public LocalDate getDate() {
		return date;
	}

	public CartType getType() {
		return type;
	}
}
