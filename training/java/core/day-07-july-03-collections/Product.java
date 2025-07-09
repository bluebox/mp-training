package day7;

import java.util.Objects;

public class Product {
	static int counter=1000;
	private int sku;
	private String name;
	private String manufacturer;
	private Category category;
	public Product(String name, String manufacturer, Category category) {
		this.sku=counter++;
		this.name = name;
		this.manufacturer = manufacturer;
		this.category = category;
	}
	@Override
	public boolean equals(Object obj) {
		Product other=(Product) obj;
		return this.sku==other.sku; 
	} 
	@Override
	public int hashCode() {
		return Objects.hash(sku); 
	}
	@Override
	public String toString() {
		return String.format("name: %s, manufacturer: %s, category:%s", name,manufacturer,category	);
	}
	
}

enum Category {
	ELECTRONICS, FURNTITURE, STATIONERY
}