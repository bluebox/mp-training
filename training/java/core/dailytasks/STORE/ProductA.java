public class ProductA extends ProductForSale {
    private String category;

    public ProductA(String type, double price, String description, String category) {
        super(type, price, description);
        this.category = category;
    }

    @Override
    public void showDetails() {
        System.out.println("--- Product Details (Type A) ---");
        System.out.println("Type: " + type);
        System.out.println("Price: $" + price);
        System.out.println("Description: " + description);
        System.out.println("Category: " + category);
        System.out.println("------------------------------");
    }
}
