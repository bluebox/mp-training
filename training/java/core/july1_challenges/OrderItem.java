package july_1;

public class OrderItem {
    private int quantity;
    private ProductForSale product;

    public OrderItem(ProductForSale product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getSalesPrice(quantity);
    }

    public void printLineItem() {
        product.printPricedItem(quantity);
    }

    public ProductForSale getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
