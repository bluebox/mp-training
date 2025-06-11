package java;

public class ProductForSale extends ProductItemsForSale {
    public ProductForSale(String type,
                          double price,
                          String description) {
        super(type, price, description);
    }

    public String toString() {
        System.out.println(type + ":");
        System.out.println("Price ::$" + price + "/-");
        System.out.println(description);
        System.out.print("-".repeat(50));


        return "";

    }

}
