public class ProductB extends ProductForSale {
    private String brand;

    public ProductB(String type, double price, String description, String brand) {
        super(type, price, description);
        this.brand = brand;
    }

    @Override
    public void showDetails() {
        System.out.println("--- Product Details (Type B) ---");
        System.out.println("Type: " + type);
        System.out.println("Price: $" + price);
        System.out.println("Description: " + description);
        System.out.println("Brand: " + brand);
        System.out.println("--------------------------------");
    }
}