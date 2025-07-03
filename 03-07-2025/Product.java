package com.prana;

public class Product {
    private final String sku;
    private final String name;
    private final String manufacturer;
    private final String category;

    public Product(String sku, String name, String manufacturer, String category) {
        this.sku = sku;
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getManufacturer() { return manufacturer; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return "%s - %s [%s]".formatted(sku, name, category);
    }
}
