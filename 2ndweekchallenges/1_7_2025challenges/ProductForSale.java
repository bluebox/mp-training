public abstract class ProductForSale {
    private String type;
    private double price;
    private String description;
    public ProductForSale(String type,double price,String description){
        this.type=type;
        this.price=price;
        this.description=description;

    }
    public void printPricedItem(int qty){
        System.out.println(qty+"price"+price+"type"+type+"description"+description);


    }
    public double getSalesPrice(int qty){
        return qty*price;

    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    abstract void showDetails();
}
