package view;

import service.OrderService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderUI {
    private final Scanner sc = new Scanner(System.in);
    private final OrderService service = new OrderService();

    public void setupTables() {
        service.setupTables();
    }

    public void insertOrder() {
        System.out.print("Enter Order ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Customer Name: ");
        String name = sc.nextLine();

        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.print("Number of Products: ");
        int count = sc.nextInt(); sc.nextLine();

        List<Map<String, Object>> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Map<String, Object> product = new HashMap<>();
            System.out.print("Product Name: ");
            product.put("product", sc.nextLine());
            System.out.print("Quantity: ");
            product.put("quantity", sc.nextInt()); sc.nextLine();
            products.add(product);
        }

        service.insert(id, name, date, products);
    }

    public void updateQuantity() {
        System.out.print("Order ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Product Name: ");
        String product = sc.nextLine();
        System.out.print("New Quantity: ");
        int qty = sc.nextInt();
        service.update(id, product, qty);
    }

    public void deleteOrder() {
        System.out.print("Order ID to delete: ");
        int id = sc.nextInt();
        service.delete(id);
    }

    public void viewOrders() {
        service.view();
    }
}
