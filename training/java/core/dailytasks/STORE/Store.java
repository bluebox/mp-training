import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Store {
    private List<ProductForSale> productsForSale;
    private List<OrderItem> currentOrder;

    public Store() {
        this.productsForSale = new ArrayList<>();
        this.currentOrder = new ArrayList<>();
    }

    public void addProductToStore(ProductForSale product) {
        productsForSale.add(product);
    }

    public void displayProducts() {
        System.out.println("\n--- Products Available in Store ---");
        System.out.printf("%-5s %-15s %-10s %-30s%n", "ID", "Type", "Price", "Description");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < productsForSale.size(); i++) {
            ProductForSale product = productsForSale.get(i);
            System.out.printf("%-5d %-15s %-10.2f %-30s%n",
                    i + 1, product.getType(), product.getPrice(), product.getDescription());
        }
        System.out.println("------------------------------------------------------------------");
    }

    public void addOrderItem(int productId, int quantity) {
        if (productId > 0 && productId <= productsForSale.size()) {
            ProductForSale product = productsForSale.get(productId - 1);
            currentOrder.add(new OrderItem(quantity, product));
            System.out.println("Added " + quantity + " x " + product.getType() + " to order.");
        } else {
            System.out.println("Invalid Product ID.");
        }
    }

    public void printOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("Your current order is empty.");
            return;
        }

        System.out.println("\n--- Your Current Order ---");
        System.out.printf("%-10s %-15s %-10s %-10s %-10s%n",
                "Qty", "Type", "Unit Price", "Subtotal", "Description");
        System.out.println("------------------------------------------------------------------");

        double total = 0;
        for (OrderItem item : currentOrder) {
            item.printItem();
            total += item.getLineItemTotal();
        }
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-45s Total: $%.2f%n", "", total);
        System.out.println("------------------------------------------------------------------");
    }

    public void finalizeOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("No order to finalize.");
            return;
        }
        System.out.println("\n--- Order Finalized ---");
        printOrder(); 
        currentOrder.clear(); 
        System.out.println("Thank you for your purchase!");
    }
}