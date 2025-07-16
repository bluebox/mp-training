package corejava.july1_AbsractClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {

    private final List<ProductsForSale> products = new ArrayList<>();
    private final List<OrderItem> order = new ArrayList<>();

    public void addProduct(ProductsForSale product) {
        products.add(product);
        //System.out.println("Product Added Successfully");
    }

    public void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(" #" + (i + 1));
            products.get(i).showDetails();
            System.out.println();
        }
    }

    public void addItemToOrder(int productIndex, int quantity) {
        if (productIndex >= 1 && productIndex <= products.size()) {
            order.add(new OrderItem(quantity, products.get(productIndex-1)));
            System.out.println("Item added to order.");
        } else {
            System.out.println("Invalid product selection.");
        }
    }

    public void printOrder() {
    	if(order.isEmpty()) {
    		System.out.println("Your have odered nothing");
    		return;
    	}
        System.out.println("Order Recipt");
        double total = 0.0;
        for (OrderItem item : order) {
            item.printItems();
            total += item.getTotal();
        }
        System.out.println("TOTAL: "+total);
        System.out.println("-------------Thank You!--------------");
    }

    public static void main(String[] args) {
        Store store = new Store();

        store.addProduct(new Product1(999.99, "Laptop"));
        store.addProduct(new Product2(29.95, "Bottle"));
        store.addProduct(new Product3(59.99, "Book"));

        Scanner sc = new Scanner(System.in);
        boolean shopping = true;

        while (shopping) {
        	System.out.println("-----Welcome to the Store -----");
            System.out.println("1. View All Products");
            System.out.println("2. Add Product to Order");
            System.out.println("3. View Sales Receipt");
            System.out.println("4. Exit");
            System.out.print("Enter your option: ");
            int option = sc.nextInt();
            switch (option) {
            case 1: store.displayProducts();
            	break;
            case 2:
                System.out.print("Enter product number: ");
                int productNum = sc.nextInt();
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                store.addItemToOrder(productNum, quantity);
                break;
            case 3: store.printOrder();
            	break;
            case 4:
                System.out.println("-----Thank you-----");
                shopping = false;
                break;
            default: System.out.println("You entered an Invalid choice. Please Try again.");
            }
        }
        sc.close();
   }    
}