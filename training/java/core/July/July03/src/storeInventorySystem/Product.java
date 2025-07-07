package storeInventorySystem;

public class Product {
	
	private String sku;
	private String name;
	private String manufacturer;
	private Category category;
	
	public Product(String sku, String name, String manufacturer, Category category) {
		this.sku = sku;
		this.name = name;
		this.manufacturer = manufacturer;
		this.category = category;
	}
	
	public String getSku() {
		return sku;
	}
 
	public String getName() {
		return name;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public Category getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", manufacturer=" + manufacturer + ", category=" + category
				+ "]";
	}
	
	
}
