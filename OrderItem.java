package abst.lpa;

public class OrderItem {
    private int quantity;
    private ProductForSale product;

    public OrderItem(int quantity, ProductForSale product) {
        this.quantity = quantity;
        this.product = product;
    }

    public void printItem() {
        product.printPricedItem(quantity);
    }

    public double getTotalPrice() {
        return product.getSalesPrice(quantity);
    }
}

