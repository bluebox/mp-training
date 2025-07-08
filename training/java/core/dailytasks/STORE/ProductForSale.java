import java.text.DecimalFormat;

public abstract class ProductForSale {
    protected String type;
    protected double price;
    protected String description;

    public ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    
    public double printPricedItem(int qty) {
        double salesPrice = getSalesPrice(qty);
        DecimalFormat df = new DecimalFormat("#.00"); 
        System.out.printf("%-10d %-15s %-10s %-10s %-10s%n",
                qty, type, df.format(price), df.format(salesPrice), description);
        return salesPrice;
    }

  
    public double getSalesPrice(int qty) {
        return qty * price;
    }

    
    public abstract void showDetails();
}