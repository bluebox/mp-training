package java;

public abstract class ProductItemsForSale {
    String type;
    double price;
    String description;

    public ProductItemsForSale(String type,
                               double price,
                               String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }
}
