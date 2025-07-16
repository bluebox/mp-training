package corejava.july1_InnerClass;

abstract class Item {
    private String name;
    private String type;
    private double price;

    public Item(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceFormatted() {
        return String.format("₹%.2f", price);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract void printItem();
}