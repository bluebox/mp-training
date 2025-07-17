package july_1;

public class ArtObject extends ProductForSale {
    public ArtObject(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {
        System.out.println("Art: " + getType());
        System.out.println("Description: " + getDescription());
        System.out.printf("Price: $%.2f\n", getPrice());
    }
}
