public class Product extends ProductForSale {
    public Product(String type, double price, String description) {
        super(type,price,description);
    }

    public void showDetails() {
        System.out.println(type + ": " + description + " - " + price);
    }
}