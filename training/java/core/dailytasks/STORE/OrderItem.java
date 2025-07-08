public class OrderItem {
    private int qty;
    private ProductForSale product;

    public OrderItem(int qty, ProductForSale product) {
        this.qty = qty;
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public ProductForSale getProduct() {
        return product;
    }

    public double getLineItemTotal() {
        return product.getSalesPrice(qty);
    }

    public void printItem() {
        product.printPricedItem(qty);
    }
}