package july_1;

import java.util.*;

public class Store {
    private static List<ProductForSale> products = new ArrayList<>();
    private static List<OrderItem> order = new ArrayList<>();

    public static void main(String[] args) {
        loadStoreItems();

        Scanner scanner = new Scanner(System.in);
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n Welcome to the Store");
            printProductCatalog();

            System.out.print("Enter product number to add to order (or -1 to checkout): ");
            int productIndex = scanner.nextInt();
            if (productIndex == -1) {
                shopping = false;
                break;
            }

            if (productIndex < 0 || productIndex >= products.size()) {
                System.out.println("Invalid product number!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();

            addItemToOrder(products.get(productIndex), qty);
        }

        printOrderSummary();
        scanner.close();
    }

    private static void loadStoreItems() {
        products.add(new ArtObject("Landscape Painting", 1500.0, "Watercolor scenery in frame"));
        products.add(new Furniture("Wooden Chair", 1200.0, "Solid teakwood "));
        products.add(new Electronics("Smartphone", 24999.0, " 8GB RAM, 128GB ROM"));
    }

    private static void printProductCatalog() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println("\nProduct #" + i);
            products.get(i).showDetails();
        }
    }

    private static void addItemToOrder(ProductForSale product, int qty) {
        order.add(new OrderItem(product, qty));
        System.out.println(" Added " + qty + " x " + product.getType() + " to your order.");
    }

    private static void printOrderSummary() {
        System.out.println("\n ORDER SUMMARY :");
        double total = 0;
        for (OrderItem item : order) {
            item.printLineItem();
            total += item.getTotalPrice();
        }
        System.out.printf("\nTOTAL: ₹%.2f\n", total);
    }
}
