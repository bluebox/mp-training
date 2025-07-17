package challenge2jdbc;

import challenge2jdbc.MenuOption;
import challenge2jdbc.OrderUI;

import java.util.Scanner;

public class OrderController {
    private final Scanner sc = new Scanner(System.in);
    private final OrderUI ui = new OrderUI();

    public void runApp() {
        ui.setupTables();

        int choice;
        do {
            System.out.println("""
                \n====== MENU ======
                1. Insert Order
                2. Update Quantity
                3. Delete Order
                4. View Orders
                0. Exit
                =================
                Enter your choice:
                """);

            choice = sc.nextInt();
            sc.nextLine();

            MenuOption option = MenuOption.fromInt(choice);

            if (option == null) {
                System.out.println(" Invalid choice.");
                continue;
            }

            switch (option) {
                case INSERT_ORDER -> ui.insertOrder();
                case UPDATE_QUANTITY -> ui.updateQuantity();
                case DELETE_ORDER -> ui.deleteOrder();
                case VIEW_ORDERS -> ui.viewOrders();
                case EXIT -> System.out.println("Exiting...");
            }

        } while (choice != 0);
    }
}
