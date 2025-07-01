public abstract class ProductForSale {
    String type;
    double price;
    String description;

    public ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public double getSalesPrice(int qty) {
        return qty * price;
    }

    public void printPricedItem(int qty) {
        double total = getSalesPrice(qty);
        System.out.println(qty + " x " + type + " = " + total);
    }

    public abstract void showDetails();
}