package july_1;

public class Item {
    private String name;
    private String type;
    private double price;

    public Item(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "%s (%s): ₹%.2f".formatted(name, type, price);
    }
}


