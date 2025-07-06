package StoreInventry;

public class Product {
	private String sku;
    private String name;
    private String manufacturer;
    private String category;
    
	public Product(String sku, String name, String manufacturer, String category) {
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

	public String getCategory() {
		return category;
	}
}
