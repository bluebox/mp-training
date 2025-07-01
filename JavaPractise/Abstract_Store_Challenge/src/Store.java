import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<OrderItem> orderList = new ArrayList<>();

    void addProduct(String type, double price, String description) {
        products.add(new Product(type,price,description));
    }

    void displayProducts() {
        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". ");
            products.get(i).showDetails();
        }
    }

    void addItemToOrder(int index, int qty) {
        if (index >= 0 && index < products.size()) {
            orderList.add(new OrderItem(qty, products.get(index)));
        }
    }

    void printOrder() {
        System.out.println("Your Bill:");
        double total = 0;
        for (OrderItem item : orderList) {
            item.product.printPricedItem(item.qty);
            total += item.product.getSalesPrice(item.qty);
        }
        System.out.println("Total: " + total);
    }
    public static void main(String[] args) {
        Store store = new Store();
        Scanner sc=new Scanner(System.in);
        int n=3;
        while(n-->0) {
        	System.out.println("Enter the Type,prize,des :");
            String type=sc.next();
            double amount=sc.nextDouble();
            String des =sc.next();
            store.addProduct(type,amount,des);
            store.displayProducts();
        }

        store.addItemToOrder(0, 2);
        store.addItemToOrder(1, 1);
        store.addItemToOrder(2, 3); 

        store.printOrder();
    }
}