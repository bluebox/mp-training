package StoreInventorySystem_CollectionsFrameworkChallenge_In_Progress;

public class Product {
	private final String sku;
	private final String productName;
	private final String manufacturer;
	private final Category category;

	public Product(String sku, String productName, String manufacturer, Category category) {
		this.sku = sku;
		this.productName = productName;
		this.manufacturer = manufacturer;
		this.category = category;
	}

	public String getSku() {
		return sku;
	}

	public String getProductName() {
		return productName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public Category getCategory() {
		return category;
	}

	@Override
	public boolean equals(Object productObj) {
		if (this == productObj) {
			return true;
		}
		if (!(productObj instanceof Product))
			return false;

		// this type casting to Product type is required, because although in runtime
		// Object type would be determined, but during compilation, compiler would not
		// be able to determine the type of productObj.
		Product newProduct = (Product) productObj;

		return sku.equals(newProduct.getSku());
	}

	@Override
	public int hashCode() {
		return sku.hashCode();
	}
}
