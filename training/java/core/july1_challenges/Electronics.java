package july_1;

public class Electronics extends ProductForSale {
    public Electronics(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {
        System.out.println("Electronics: " + getType());
        System.out.println("Specs: " + getDescription());
        System.out.printf("Price: $%.2f\n", getPrice());
    }
}
