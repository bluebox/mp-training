package Model;

public class Membership {
    private int id;
    private String name;
    private int durationMonths;
    private double price;

    public Membership() {}

    public Membership(int id, String name, int durationMonths, double price) {
        this.id = id;
        this.name = name;
        this.durationMonths = durationMonths;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getDurationMonths() { return durationMonths; }
    public void setDurationMonths(int durationMonths) { this.durationMonths = durationMonths; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
