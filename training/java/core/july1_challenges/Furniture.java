package july_1;

public class Furniture extends ProductForSale {
    public Furniture(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {
        System.out.println("Furniture: " + getType());
        System.out.println("Material: " + getDescription());
        System.out.printf("Price: $%.2f\n", getPrice());
    }
}
