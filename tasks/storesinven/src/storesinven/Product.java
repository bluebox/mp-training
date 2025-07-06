package storesinven;

import java.util.UUID;

public class Product {
    private UUID sku;
    private String name;

    public Product(String name) {
        this.name = name;
        this.sku = UUID.randomUUID();
    }

    public UUID getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }
}

