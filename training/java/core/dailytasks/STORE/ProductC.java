
public class ProductC extends ProductForSale {
    private String material;

    public ProductC(String type, double price, String description, String material) {
        super(type, price, description);
        this.material = material;
    }

    @Override
    public void showDetails() {
        System.out.println("--- Product Details (Type C) ---");
        System.out.println("Type: " + type);
        System.out.println("Price: $" + price);
        System.out.println("Description: " + description);
        System.out.println("Material: " + material);
        System.out.println("--------------------------------");
    }
}