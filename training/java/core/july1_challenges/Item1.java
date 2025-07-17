package july_1;

public class Item1{
    protected String name;
    protected String type;
    protected double price;

    public Item1(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public double getPriceInUSD() {
        return price;
    }

    public double getPriceInINR() {
        return price * 83.0;
    }

    @Override
    public String toString() {
        return name + " (" + type + "): $" + String.format("%.2f", price);
    }
}
